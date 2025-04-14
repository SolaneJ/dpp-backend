package com.github.solanej.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * webConfig
 *
 * @since 2025/4/1 18:31
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        /* 设置支持的媒体类型为文本类型，如果未设置，默认会使用application/json，在请求微信接口（默认返回text_plain）时会报UnKnownMediaTypeException */
        // 源码分析：https://blog.csdn.net/weixin_43901865/article/details/120430681
        // 解决方案：https://blog.csdn.net/qq_44732146/article/details/130391606
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.TEXT_PLAIN));

        // 将消息转换器添加到RestTemplate中
        restTemplate.getMessageConverters().addFirst(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }
}
