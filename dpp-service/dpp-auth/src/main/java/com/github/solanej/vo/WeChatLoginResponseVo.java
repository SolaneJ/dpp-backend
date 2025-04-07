package com.github.solanej.vo;

/**
 * WechatLoginResponseVo
 *
 * @since 2025/4/1 14:00
 */
public record WeChatLoginResponseVo(String session_key, String openid, String errcode, String errmsg) {

}
