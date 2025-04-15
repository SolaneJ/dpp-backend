package com.github.solanej.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.github.solanej.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.PostConstruct;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

/**
 * 网关配置类
 *
 * @since 2025/3/26 23:03
 */
@Configuration
public class GatewayConfiguration implements GlobalFilter, Ordered {

    @Bean
    public GlobalFilter globalFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * 硬编码方式手动添加限流规则，后续可以通过数据库或者Nacos等配置中心动态添加限流规则
     * <a href="https://sentinelguard.io/zh-cn/docs/dynamic-rule-configuration.html">...</a>
     */
    @PostConstruct
    public void initGatewayRules() {
        final Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("dpp-auth")        // 资源名称，对应路由id
                .setCount(1)                                     // 限流阈值
                .setIntervalSec(5));                             // 统计时间窗口，单位秒
        GatewayRuleManager.loadRules(rules);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        final ServerHttpRequest request = exchange.getRequest();
        // 获取请求头中的Authorization
        final String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // 如果没有Authorization头，直接返回401
        if (token == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            Claims claims = TokenUtil.parseToken(token);
            String userId = claims.get("openid", String.class);

            // 把用户信息加入请求头传给下游服务
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-Id", userId)
                    .build();

            // 打印请求头和请求路径的调试信息
            System.out.println("Request Headers: " + mutatedRequest.getHeaders());
            System.out.println("Request Path: " + mutatedRequest.getPath());
            System.out.println("Request Method: " + mutatedRequest.getMethod());


            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -1; // 优先级高
    }
}
