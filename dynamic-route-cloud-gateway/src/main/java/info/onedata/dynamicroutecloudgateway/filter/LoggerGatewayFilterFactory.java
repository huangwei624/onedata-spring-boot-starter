package info.onedata.dynamicroutecloudgateway.filter;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2022/1/4 16:04
 * @description: LoggerGatewayFilterFactory
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

/**
 * @author Spencer Gibb
 */
public class LoggerGatewayFilterFactory
        extends AbstractGatewayFilterFactory<LoggerGatewayFilterFactory.Config> {

    private static final Logger log = LoggerFactory.getLogger(LoggerGatewayFilterFactory.class);

    /**
     * version key.
     */
    public static final String VERSION = "version";

    /**
     * application_name key.
     */
    public static final String APPLICATION_NAME = "application_name";

    public LoggerGatewayFilterFactory() {
        super(LoggerGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(VERSION, APPLICATION_NAME);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest req = exchange.getRequest();
                String remoteHost = Objects.requireNonNull(req.getRemoteAddress()).getHostString();
                String uri = req.getURI().getRawPath();
                log.info("applicationName:{}, remoteHost: {}, uri: {}, version: {}",
                        config.getApplicationName(), remoteHost, uri, config.getVersion());
                return chain.filter(exchange);
            }

            @Override
            public String toString() {
                return filterToStringCreator(LoggerGatewayFilterFactory.this)
                        .append(config.getVersion(), config.getApplicationName()).toString();
            }
        };
    }

    public static class Config {

        private String applicationName;

        private String version;

        public Config() {
        }

        public String getApplicationName() {
            return applicationName;
        }

        public void setApplicationName(String applicationName) {
            this.applicationName = applicationName;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

}
