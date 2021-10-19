package info.onedata.dynamic.datasource.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "info.onedata.dynamic.datasource.mybatisplus.**.dao")
@SpringBootApplication
public class DynamicDatasourceMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceMybatisPlusApplication.class, args);
    }

}
