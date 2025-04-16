package com.github.solanej.vo;

import java.util.Date;

/**
 * UserProfile
 *
 * @since 2025/4/16 13:01
 */
public record UserProfile(
        String dUsername,
        String dSex,
        Date dBirth,
        String dPhone,
        String dAvatar,
        String dRemark
) {
}
