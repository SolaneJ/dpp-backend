package com.github.solanej.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * HashUtil
 *
 * @since 2025/4/15 12:12
 */
public class HashUtil {

    private static final String SALT = System.getenv("SALT");

    public static String hash(String input) {
        return hash(input + SALT, "SHA3-256");  // 可改为 SHA3-224 / SHA3-384 / SHA3-512
    }

    public static String hash(String input, String algorithm) {
        try {
            final MessageDigest md = MessageDigest.getInstance(algorithm);
            // 计算哈希值
            final byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            // 将字节数组转换为十六进制字符串
            return bytesToHex(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while hashing with " + algorithm, e);
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        final StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
