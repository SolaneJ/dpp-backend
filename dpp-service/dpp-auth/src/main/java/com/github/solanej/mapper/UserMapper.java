package com.github.solanej.mapper;

import com.github.solanej.dao.DppUser;
import com.github.solanej.vo.LoginRequestVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户表查询接口
 *
 * @since 2025/3/31 01:00
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT IGNORE INTO dpp_user(d_openid) VALUES(#{openid})")
    void insertUserWithOpenId(String openid);

    @Select("SELECT * FROM dpp_user WHERE d_email = #{username}")
    DppUser login(LoginRequestVo loginRequestVo);
}
