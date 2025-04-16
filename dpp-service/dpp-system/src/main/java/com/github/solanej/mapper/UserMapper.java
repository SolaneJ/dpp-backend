package com.github.solanej.mapper;

import com.github.solanej.dao.DppUser;
import com.github.solanej.vo.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * UserMapper
 *
 * @since 2025/4/16 12:46
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserProfile toUserProfile(DppUser dppUser);
}
