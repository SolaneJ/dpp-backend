package com.github.solanej.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * TestController
 *
 * @since 2025/3/20 08:13
 */
@Profile("dev")
@RestController
public class TestController {

    private final WebClient webClient;

    public TestController(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    @RequestMapping("/test")
    public Mono<String> test() {
        return webClient.get()
                .uri("lb://dpp-auth/login")
                .retrieve()
                .bodyToMono(String.class);
    }
}
