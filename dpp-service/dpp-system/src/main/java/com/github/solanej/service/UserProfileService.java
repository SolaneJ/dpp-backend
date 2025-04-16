package com.github.solanej.service;

import com.github.solanej.view.ResponseData;
import org.springframework.web.multipart.MultipartFile;

/**
 * UserProfileService
 *
 * @since 2025/4/14 23:19
 */
public interface UserProfileService {

    // 上传头像
    ResponseData uploadAvatar(String openid, MultipartFile file);

    // 获取用户信息
    ResponseData getUserProfile(String openid);
}
