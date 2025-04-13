package com.github.solanej.mapper;

import com.github.solanej.dao.DppUser;
import com.github.solanej.vo.LoginRequestVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户表查询接口
 *
 * @since 2025/3/31 01:00
 */
@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     *
     * @return 用户信息
     */
    @Select("SELECT * FROM dpp_user WHERE d_openid = #{openid}")
    DppUser findByOpenId(String openid);

    @Insert("INSERT IGNORE INTO dpp_user(d_openid) VALUES(#{openid})")
    void insertUserWithOpenId(String openid);

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return 用户信息
     */
    DppUser findByPhone(String phone);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @Select("SELECT * FROM dpp_user")
    List<DppUser> findAll();

    @Select("SELECT * FROM dpp_user WHERE d_email = #{username}")
    DppUser login(LoginRequestVo loginRequestVo);
}
