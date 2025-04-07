package com.github.solanej.mapper;

import com.github.solanej.dao.DppUser;
import com.github.solanej.vo.LoginRequestVo;
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
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    DppUser findByUsername(String username);

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户信息
     */
    DppUser findByEmail(String email);

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
