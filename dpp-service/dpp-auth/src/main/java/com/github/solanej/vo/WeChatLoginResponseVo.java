package com.github.solanej.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * WechatLoginResponseVo
 *
 * @since 2025/4/1 14:00
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record WeChatLoginResponseVo(String session_key, String openid, String errcode, String errmsg) {

}
