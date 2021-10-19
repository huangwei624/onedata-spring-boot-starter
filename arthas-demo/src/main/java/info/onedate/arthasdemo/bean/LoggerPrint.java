package info.onedate.arthasdemo.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/5 16:21
 * @description: LoggerPrint
 */
@Component
public class LoggerPrint {

    private static final Logger log = LoggerFactory.getLogger(LoggerPrint.class);

    private static SimpleClass simpleClass = new SimpleClass();

    public void printLog() throws InterruptedException {
        while (true) {
            log.info("log level is info...");
            TimeUnit.SECONDS.sleep(1);
            log.warn("log level is warn...");
            TimeUnit.SECONDS.sleep(1);
            log.debug("log lever is debug ...");
            TimeUnit.SECONDS.sleep(1);
            log.trace("log level is trace...");
            getNumber(1, Collections.singletonList(1));
        }
    }

    /**
     * 获取一个数字
     * @param random
     * @param list
     * @return
     */
    public static Integer getNumber(Integer random, List<Integer> list) {
        long count = list.stream().mapToInt(item -> item * 2).sum();
        return ((int) (random + count));
    }


}
