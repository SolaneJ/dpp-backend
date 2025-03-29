package com.github.solanej.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 负载均衡配置类
 *
 * @since 2025/3/13 18:51
 */
@Configuration
public class LoadBalancedConfig {

    /**
     * 在Netty框架中，WebClient是一个非阻塞、响应式的客户端，它支持异步和事件驱动的方式来处理请求。
     * 而RestTemplate是一个阻塞的客户端，它是基于Servlet的，它的请求是同步的，也就是说发送一个请求后，会一直等待响应。
     * <p>
     * Spring-gateway中使用了Netty网络框架，是基于WebFlux，对标的是SpringMVC的一个特性，所以官方推荐使用WebClient。
     *
     * @deprecated
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
