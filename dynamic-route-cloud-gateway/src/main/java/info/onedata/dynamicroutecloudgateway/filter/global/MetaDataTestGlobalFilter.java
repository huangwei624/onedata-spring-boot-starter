package info.onedata.dynamicroutecloudgateway.filter.global;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/4 11:16
 * @description: MetaDataTestGlobalFilter
 */
public class MetaDataTestGlobalFilter implements GlobalFilter, Ordered {
    public static Logger logger = LoggerFactory.getLogger(MetaDataTestGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("exchange: {}", exchange.getClass().getName());
        Map<String, Object> attributes = exchange.getAttributes();
        logger.info("attr: {}", JSONObject.toJSONString(attributes));
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        if (route != null) {
            logger.info("route: {}", route.toString());
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
