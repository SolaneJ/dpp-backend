package com.github.solanej;

import com.github.solanej.utils.TokenUtil;
import lombok.extern.java.Log;
import org.junit.Test;

import java.util.HashMap;

/**
 * com.github.solanej.TestTokenUtils
 *
 * @since 2025/3/12 13:31
 */
@Log
public class TestTokenUtil {

    @Test
    public void generateAndGetInfoFromToken() {

        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("openId", "oa4ZW6m3PuZunLGNmpRvvMkI7ZYs");
        userInfo.put("userId", "123456");
        userInfo.put("userName", "张三");

        String token = TokenUtil.createToken(userInfo);

        log.info("Generated Token: " + token);

        boolean isValid = TokenUtil.validateToken(token);
        log.info("Is Token Valid: " + isValid);

        if (isValid) {
            var claims = TokenUtil.parseToken(token);
            log.info("Parsed Token Claims: " + claims);
            log.info("User ID: " + claims.get("userId"));
            log.info("User Name: " + claims.get("userName"));
        } else {
            log.warning("Token is invalid or expired.");
        }
    }
}
