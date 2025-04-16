package com.github.solanej.controller;

import com.github.solanej.service.UserProfileService;
import com.github.solanej.view.ResponseData;
import com.github.solanej.vo.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息管理
 *
 * @since 2025/4/14 17:23
 */
@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    /**
     * 上传头像
     *
     * @param file 前端通过form-data传递的文件
     * @return 头像地址url
     */
    @PutMapping("/uploadAvatar")
    public ResponseData uploadAvatar(@RequestHeader("X-User-Id") String openid, @RequestParam("file") MultipartFile file) {
        return userProfileService.uploadAvatar(openid, file);
    }

    /**
     * 更新用户信息（局部更新）
     */
    @PatchMapping("/updateUserProfile")
    public ResponseData updateUserProfile(@RequestHeader("X-User-Id") String openid, @RequestBody UserProfile userProfile) {
        return userProfileService.updateUserProfile(openid, userProfile);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getUserProfile")
    public ResponseData getUserProfile(@RequestHeader("X-User-Id") String openid) {
        return userProfileService.getUserProfile(openid);
    }
}
