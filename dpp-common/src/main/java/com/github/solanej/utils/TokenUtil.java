package com.github.solanej.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

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
public class TokenUtil {

    private static final String secret = "your256bitsecretwhichshouldbebase64encodedyufvghkytidcyghk";
    private static final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

    // 构建token
    public static String createToken(Map<String, Object> userData) {
        return Jwts.builder()
                .claims(userData)
                // 设置签发时间
                .issuedAt(Date.from(Instant.now()))
                // 设置签名
                .signWith(key)
                // 设置过期时间expiration
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .compact();
    }

    // 验证token
    public static boolean validateToken(String token) {
        try {
            Jws<Claims> jwsClaims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            // token过期
            return false;
        } catch (Exception e) {
            // token无效
            return false;
        }
        return true;
    }

    // 解析token
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token).getPayload();
    }
}