package com.github.solanej.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.github.solanej.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

/**
 * 网关配置类
 *
 * @since 2025/3/26 23:03
 */
@Slf4j
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

        // 判断是否为白名单请求
        if (isWhiteListRequest(exchange)) {
            return chain.filter(exchange);
        }

        // 获取当前请求和响应对象
        final ServerHttpRequest request = exchange.getRequest();
        final ServerHttpResponse response = exchange.getResponse();

        // 从请求头中获取Authorization字段作为token
        final String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // 检查token是否存在，如果不存在则返回401未授权状态码
        if (token == null) {
            log.warn("请求 [{}] 缺少 Authorization 头，返回 401 未授权", request.getURI());
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        try {
            // 调用工具类解析token，获取用户信息
            Claims claims = TokenUtil.parseToken(token);
            String userId = claims.get("openid", String.class);

            // 将用户ID添加到请求头中，传递给下游服务
            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("X-User-Id", userId)
                    .build();

            // 继续执行过滤器链
            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        } catch (Exception e) {
            // 记录解析token时发生的异常，并返回500内部服务器错误状态码
            log.error("解析token时发生异常: ", e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return response.setComplete();
        }
    }

    private boolean isWhiteListRequest(ServerWebExchange exchange) {
        // 获取请求的URI
        final String uri = exchange.getRequest().getURI().toString();

        // 定义白名单列表
        final String[] whiteList = {
                "/auth/login",
                "/auth/register",
                "/auth/refreshToken"
        };

        // 检查请求URI是否在白名单中
        for (String path : whiteList) {
            if (uri.contains(path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return -1; // 优先级高
    }
}
