package com.github.solanej.dao;

import java.sql.Date;
import java.sql.Timestamp;

public record DppUser(
        int dId,
        String dOpenid,
        String dUsername,
        String dPassword,
        String dSex,
        Date dBirth,
        String dEmail,
        String dPhone,
        Timestamp dCreateTime,
        Timestamp dUpdateTime,
        String dAvatar,
        Integer dStatus,
        String dRemark
) {
}