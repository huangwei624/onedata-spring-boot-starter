package info.onedata.swagger.example;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: huangwei
 * @Date: 2021/12/1 15:43
 * @Description: JustTest
 * @Version 1.0.0
 */
public class JustTest {

    //
    // -XX:-UseParallelOldGC
    // [Full GC (Ergonomics) [PSYoungGen: 12577K->8192K(18944K)] [PSOldGen: 38538K->42634K(44032K)] 51115K->50826K(62976K), [Metaspace: 5490K->5490K(1056768K)], 0.0084351 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]


    //  -XX:+UseParallelOldGC
    // [Full GC (Allocation Failure) [PSYoungGen: 9845K->9749K(18944K)] [ParOldGen: 40973K->40965K(44032K)] 50818K->50715K(62976K), [Metaspace: 5482K->5479K(1056768K)], 0.0053157 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

    @Test
    public void t1() throws InterruptedException {
        ArrayList<byte[]> list = new ArrayList<>();
        while (true) {
            byte[] bytes = new byte[1024 * 1024 * 8];
            list.add(bytes);
            TimeUnit.SECONDS.sleep(1);
        }
    }


    @Test
    public void t2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("t1");
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        Object o = new Object();

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (o) {
                    System.out.println("t2");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
            while (true) {
                synchronized (o) {
                    System.out.println("t3");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t3");
        t3.start();

        Thread t4 = new Thread(() -> {
            while (true) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t4");
        t4.start();

        TimeUnit.HOURS.sleep(1);
    }

    @Test
    public void t3() throws InterruptedException {


        ReentrantLock reentrantLock = new ReentrantLock();

        Thread t2 = new Thread(() -> {
            while (true) {
                reentrantLock.lock();
                System.out.println("t2");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
            while (true) {
                reentrantLock.lock();
                System.out.println("t3");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            }
        }, "t3");
        t3.start();

        TimeUnit.HOURS.sleep(1);
    }



}
