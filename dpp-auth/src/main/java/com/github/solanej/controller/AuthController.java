package com.github.solanej.controller;

import com.github.solanej.view.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 *
 * @since 2025/3/20 07:56
 */
@RestController
public class AuthController {

    @RequestMapping("/login")
    public ResponseData login() {
        return ResponseData.success(2);
    }
}
