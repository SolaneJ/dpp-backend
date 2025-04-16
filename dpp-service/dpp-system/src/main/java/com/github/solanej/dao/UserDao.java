package com.github.solanej.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * UserDao
 *
 * @since 2025/4/16 13:14
 */
@Mapper
public interface UserDao {

    @Select("SELECT * FROM dpp_user WHERE d_openid = #{openid}")
    DppUser getUserProfileByOpenId(String openid);
}
