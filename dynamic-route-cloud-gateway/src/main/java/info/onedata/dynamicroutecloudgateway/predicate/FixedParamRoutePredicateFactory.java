package info.onedata.dynamicroutecloudgateway.predicate;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/5 10:07
 * @description: FixedParam
 */

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Spencer Gibb
 */
public class FixedParamRoutePredicateFactory extends AbstractRoutePredicateFactory<FixedParamRoutePredicateFactory.Config> {

    /**
     * DateTime key.
     */
    public static final String PARAM_NAME = "name";
    public static final String PARAM_VALUE = "value";

    public FixedParamRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_NAME, PARAM_VALUE);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                ServerHttpRequest request = serverWebExchange.getRequest();
                MultiValueMap<String, String> queryParams = request.getQueryParams();
                List<String> paramValues = queryParams.get(config.getName());
                if (CollectionUtils.isEmpty(paramValues)) {
                    return false;
                }
                return paramValues.contains(config.getValue());
            }

            @Override
            public String toString() {
                return String.format("name: %s, value: %s", config.getName(), config.getValue());
            }
        };
    }

    public static class Config {

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
