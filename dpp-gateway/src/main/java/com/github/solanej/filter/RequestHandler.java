package com.github.solanej.filter;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import lombok.extern.java.Log;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * RequestHandler
 *
 * @since 2025/3/25 19:01
 */
@Log
@Component
public class RequestHandler implements WebFilter {

    /**
     * 拦截所有webflux请求
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 检查是否有 Authorization 头
//        if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
//            ServerHttpResponse response = exchange.getResponse();
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
//        }
        log.info(exchange.getRequest().getURI().toString());
        return chain.filter(exchange);
    }
}
