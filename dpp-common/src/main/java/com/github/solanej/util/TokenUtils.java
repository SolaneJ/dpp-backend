package com.github.solanej.util;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * Jwt(Json Web Token)工具类
 *
 * @since 2025/3/12 08:43
 */
public class TokenUtils {

    private static final SecretKey key = Jwts.SIG.HS512.key().build();

    // 构建token
    public static String createToken(Map<String, Object> userData) {
        return Jwts.builder()
                .claims(userData)
                // 设置签发时间
                .issuedAt(Date.from(Instant.now()))
                // 设置签名
                .signWith(key, Jwts.SIG.HS512)
                // 设置过期时间expiration
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.MINUTES)))
                .compact();
    }

    // todo 解析token
    public static Map<String, Object> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token).getPayload();
    }
}