package com.github.solanej.service.impl;

import com.github.solanej.mapper.UserMapper;
import com.github.solanej.service.AuthService;
import com.github.solanej.view.ResponseData;
import com.github.solanej.vo.LoginRequestVo;
import com.github.solanej.vo.WeChatLoginRequestVo;
import com.github.solanej.vo.WeChatLoginResponseVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 认证服务接口实现类
 *
 * @since 2025/3/26 16:37
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final RestTemplate restTemplate;

    public AuthServiceImpl(UserMapper userMapper, RestTemplate restTemplate) {
        this.userMapper = userMapper;
        this.restTemplate = restTemplate;
    }

    @Value("${WXAPPID}")
    private String appid;
    @Value("${WXAPPSECRET}")
    private String appsecret;

    @Deprecated
    public ResponseData login(LoginRequestVo loginRequestVo) {
        return ResponseData.success(userMapper.login(loginRequestVo));
    }

    @Override
    public ResponseData wxLogin(WeChatLoginRequestVo wechatLoginRequestVo) {

        // 根据前端传来的code，获取session_key和openid
        // 自定义登录态，与session_key和openid关联
        final WeChatLoginResponseVo data = restTemplate.getForObject(
                "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + appsecret + "&js_code=" + wechatLoginRequestVo.code() + "&grant_type=authorization_code",
                WeChatLoginResponseVo.class,
                new HashMap<>());
        return ResponseData.success(1);
    }
}
