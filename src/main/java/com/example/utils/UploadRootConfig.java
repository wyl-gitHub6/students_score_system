package com.example.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * tomacat 配置虚拟目录
 * @author hasee
 */
@Configuration
public class UploadRootConfig implements WebMvcConfigurer {

    /**
     * addResourceHandler注册项目名称
     * addResourceLocations指定访问的本地路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image_upload/**")
                .addResourceLocations("file:D:\\image_upload\\");
    }
}
