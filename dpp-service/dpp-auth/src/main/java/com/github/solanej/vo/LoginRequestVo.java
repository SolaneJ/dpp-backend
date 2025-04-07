package com.github.solanej.vo;

import jakarta.annotation.Nonnull;

/**
 * 请求登录视图层
 *
 * @since 2025/3/31 00:36
 */
public record LoginRequestVo(@Nonnull String username, @Nonnull String password) {

}
