package com.github.solanej.config;

import com.github.solanej.filter.RequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Spring Security配置类
 *
 * @since 2025/3/21 09:29
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * 基于Spring security的配置过滤器
     *
     * @param http ServerHttpSecurity
     * @return SecurityWebFilterChain
     */
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorizeExchangeSpec -> {
                    authorizeExchangeSpec
//                            .pathMatchers("/auth/wxLogin").permitAll()
                            .anyExchange().permitAll()
                    ;
                })
                .addFilterBefore(new RequestHandler(), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
}