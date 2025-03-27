package com.github.solanej.service;

import com.github.solanej.view.ResponseData;

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
    ResponseData login(String username, String password);
}
