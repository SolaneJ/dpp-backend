package com.github.solanej.service;

import com.github.solanej.view.ResponseData;
import com.github.solanej.vo.WeChatLoginRequestVo;

/**
 * 认证服务功能接口
 *
 * @since 2025/3/26 16:36
 */
public interface AuthService {

    /**
     * 微信小程序登录获取session
     */
    ResponseData wxLogin(WeChatLoginRequestVo wechatLoginRequestVo);
}