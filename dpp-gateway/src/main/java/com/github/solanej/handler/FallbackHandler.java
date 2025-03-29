package com.github.solanej.handler;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import com.github.solanej.view.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Sentinel 限流处理器
 *
 * @since 2025/3/29 16:58
 */
@Component
public class FallbackHandler implements BlockRequestHandler{

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable throwable) {
        return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(ResponseData.failed("请求过于频繁，请稍后再试！", 429));
    }
}
