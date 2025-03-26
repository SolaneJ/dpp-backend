package com.github.solanej;

import com.github.solanej.util.TokenUtils;
import lombok.extern.java.Log;
import org.junit.Test;

import java.util.HashMap;

/**
 * com.github.solanej.TestTokenUtils
 *
 * @since 2025/3/12 13:31
 */
@Log
public class TestTokenUtils {

    @Test
    public void generateAndGetInfoFromToken() {

        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", "123456");
        userInfo.put("userName", "张三");

        String token = TokenUtils.createToken(userInfo);

        log.info("Generated Token: " + token);

        boolean isValid = TokenUtils.validateToken(token);
        log.info("Is Token Valid: " + isValid);

        if (isValid) {
            var claims = TokenUtils.parseToken(token);
            log.info("Parsed Token Claims: " + claims);
            log.info("User ID: " + claims.get("userId"));
            log.info("User Name: " + claims.get("userName"));
        } else {
            log.warning("Token is invalid or expired.");
        }
    }
}
