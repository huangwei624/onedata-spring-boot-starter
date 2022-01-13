package info.onedate.arthasdemo;

import info.onedate.arthasdemo.bean.LoggerPrint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ArthasDemoApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ArthasDemoApplication.class, args);
        LoggerPrint bean = applicationContext.getBean(LoggerPrint.class);
        bean.printLog();
    }

}
