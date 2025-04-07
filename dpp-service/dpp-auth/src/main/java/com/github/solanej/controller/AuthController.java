package com.github.solanej.controller;

import com.github.solanej.service.AuthService;
import com.github.solanej.view.ResponseData;
import com.github.solanej.vo.LoginRequestVo;
import com.github.solanej.vo.WeChatLoginRequestVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证服务，提供登录、注册、注销等功能
 *
 * @since 2025/3/20 07:56
 */
@RestController
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 登录
     */
    @RequestMapping("/login")
    public ResponseData login(@RequestBody LoginRequestVo loginRequestVo) {
        return authService.login(loginRequestVo);
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping("/register")
    public ResponseData register(@RequestBody String jsonStr) {
        return null;
    }

    @RequestMapping("/wxLogin")
    public ResponseData wxLogin(@RequestBody WeChatLoginRequestVo wechatLoginRequestVo) {
        return authService.wxLogin(wechatLoginRequestVo);
    }
}
