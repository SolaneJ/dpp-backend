package com.github.solanej.service;

import com.github.solanej.view.ResponseData;
import com.github.solanej.vo.LoginRequestVo;
import com.github.solanej.vo.WeChatLoginRequestVo;

/**
 * 认证服务功能接口
 *
 * @since 2025/3/26 16:36
 */
public interface AuthService {

    /**
     * 登录
     *
     * @return 包含登录结果的ResponseData对象
     */
    ResponseData login(LoginRequestVo loginRequestVo);

    /**
     * 微信小程序登录获取session
     */
    ResponseData wxLogin(WeChatLoginRequestVo wechatLoginRequestVo);
}