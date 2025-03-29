package com.github.solanej.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import jakarta.annotation.PostConstruct;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * 网关配置类
 *
 * @since 2025/3/26 23:03
 */
@Configuration
public class GatewayConfiguration {

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
}
