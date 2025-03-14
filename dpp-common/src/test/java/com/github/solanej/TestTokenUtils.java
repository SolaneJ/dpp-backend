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
    public void generateToken() {

        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", "123456");
        userInfo.put("userName", "张三");

        log.info(TokenUtils.createToken(userInfo));
    }
}
