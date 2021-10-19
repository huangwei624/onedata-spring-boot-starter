package info.onedata.dynamicroutecloudgateway.config;

import info.onedata.dynamicroutecloudgateway.filter.LoggerGatewayFilterFactory;
import info.onedata.dynamicroutecloudgateway.filter.global.MetaDataTestGlobalFilter;
import info.onedata.dynamicroutecloudgateway.predicate.FixedParamRoutePredicateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/4 10:58
 * @description: GatewayConfig
 */
@Configuration
public class GatewayConfig {

    @PostConstruct
    public void init() {

    }

    @Bean
    public LoggerGatewayFilterFactory loggerGatewayFilterFactory() {
        return new LoggerGatewayFilterFactory();
    }

    @Bean
    public MetaDataTestGlobalFilter metaDataTestGlobalFilter() {
        return new MetaDataTestGlobalFilter();
    }

    @Bean
    public FixedParamRoutePredicateFactory fixedParamRoutePredicateFactory() {
        return new FixedParamRoutePredicateFactory();
    }

}
