package com.github.solanej.service.impl;

import com.github.solanej.mapper.UserMapper;
import com.github.solanej.service.AuthService;
import com.github.solanej.util.TokenUtils;
import com.github.solanej.view.ResponseData;
import com.github.solanej.vo.LoginRequestVo;
import com.github.solanej.vo.WeChatLoginRequestVo;
import com.github.solanej.vo.WeChatLoginResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务接口实现类
 *
 * @since 2025/3/26 16:37
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final RestTemplate restTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public AuthServiceImpl(UserMapper userMapper, RestTemplate restTemplate, StringRedisTemplate stringRedisTemplate) {
        this.userMapper = userMapper;
        this.restTemplate = restTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Value("${WX_MINIPROGRAM_APPID}")
    private String appid;

    @Value("${WX_MINIPROGRAM_SECRET}")
    private String appsecret;

    @Deprecated
    public ResponseData login(LoginRequestVo loginRequestVo) {
        return ResponseData.success(userMapper.login(loginRequestVo));
    }

    @Override
    public ResponseData wxLogin(WeChatLoginRequestVo wechatLoginRequestVo) {

        // 根据前端传来的code，获取session_key和openid
        // 调用微信接口获取用户的 session_key 和 openid
        WeChatLoginResponseVo data;

        try {
            // 使用 RestTemplate 调用微信的 jscode2session 接口
            data = restTemplate.getForObject(
                    "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + appsecret + "&js_code=" + wechatLoginRequestVo.code() + "&grant_type=authorization_code",
                    WeChatLoginResponseVo.class,
                    new HashMap<>());

        } catch (Exception e) {
            // 如果接口调用失败，返回错误信息
            log.error(e.getMessage());
            return ResponseData.failed("微信接口调用失败，请稍后重试", 500);
        }

        // 获取 openid
        final String openid = data.openid();

        // 如果 openid 为空，说明获取 session_key 失败
        if (openid == null) {
            return ResponseData.failed("获取session_key失败，请稍后再试", 400);
        }

        // 将 session_key 缓存到 Redis 中，设置有效期为 1 天
        // 如果 Redis 已经存在了openid，则会刷新过期时间
        stringRedisTemplate.opsForValue().set(openid, data.session_key(), Duration.ofDays(1));

        // 构造用户数据，用于生成 token
        final Map<String, Object> userData = Map.of("openid", openid);

        // 生成 token
        final String token = TokenUtils.createToken(userData);

        // 执行添加数据库的操作
        userMapper.insertUserWithOpenId(openid);

        // 生成并返回 token
        return ResponseData.success(token);
    }
}
