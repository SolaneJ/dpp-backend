package com.github.solanej.controller;

import com.github.solanej.service.AuthService;
import com.github.solanej.view.ResponseData;
import com.github.solanej.vo.WeChatLoginRequestVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 微信小程序 登录接口
     *
     * @param wechatLoginRequestVo 携带用于请求wx.code2session接口的{code}
     */
    @GetMapping("/wxLogin")
    public ResponseData wxLogin(@RequestBody WeChatLoginRequestVo wechatLoginRequestVo) {
        return authService.wxLogin(wechatLoginRequestVo);
    }
}
