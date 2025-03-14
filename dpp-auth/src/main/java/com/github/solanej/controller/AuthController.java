package com.github.solanej.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 认证接口
 *
 * @since 2025/3/12 08:39
 */
@RestController
public class AuthController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/aaa")
    public String aaa() {
        return "aaa";
    }
}
