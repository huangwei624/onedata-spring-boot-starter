package info.onedate.arthasdemo.bean;

import java.util.Random;
import java.util.RandomAccess;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/5 18:42
 * @description: SimpleClass
 */
public class SimpleClass {

    private static Random random = new Random();

    /**
     * 随机一个 10 -1000 的数字
     * @return
     */
    public static Integer randomNumber() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        return threadLocalRandom.nextInt(10, 1000);
    }

    /**
     * 随机获取一个字符串
     * @return
     */
    public String randomString() {
        return UUID.randomUUID().toString();
    }

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        SimpleClass.random = random;
    }
}
