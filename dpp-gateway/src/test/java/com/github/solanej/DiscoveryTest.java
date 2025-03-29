package com.github.solanej;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

/**
 * 服务发现测试类
 *
 * @since 2025/3/19 08:21
 */
@Log
@SpringBootTest
public class DiscoveryTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    void getInstances() {
        for (String service : discoveryClient.getServices()) {
            log.info(service);
        }
    }
}
