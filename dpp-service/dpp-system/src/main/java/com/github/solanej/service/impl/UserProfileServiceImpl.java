package com.github.solanej.service.impl;

import com.github.solanej.service.UserProfileService;
import com.github.solanej.view.ResponseData;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

import static com.github.solanej.constant.UserProfileConstant.AVATAR_BUCKET;
import static com.github.solanej.utils.HashUtil.hash;

/**
 * UserProfileServiceImpl
 *
 * @since 2025/4/14 23:27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final MinioClient minioClient;

    @Override
    public ResponseData uploadAvatar(String openid, MultipartFile file) {

        // 获取原始文件名
        final String originalFilename = file.getOriginalFilename();

        // 提取文件后缀
        String ext = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 生成目标文件名
        String targetObjectName = hash(openid) + ext;

        try {
            // 上传文件到 MinIO 对象服务器
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(AVATAR_BUCKET)
                            .object(targetObjectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build());

            // 再获取对象服务器中的图片，以Base64的形式返回给前端
            // 这里可以使用 MinIO 的 getObject 方法获取文件内容，并将其转换为 Base64 字符串

            final byte[] bytes = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(AVATAR_BUCKET)
                            .object(targetObjectName)
                            .build()).readAllBytes();

            final String base64 = Base64.getEncoder().encodeToString(bytes);

            return ResponseData.success(base64);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseData.error(500);
        }
    }
}
