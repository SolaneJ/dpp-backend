package com.github.solanej.filter;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 请求拦截器
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
        // Todo 验证token，如果token不合法，返回401
        return chain.filter(exchange);
    }
}
