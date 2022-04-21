package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置虚拟目录
 *
 * @author Wangyl
 * @date 2022/04/21
 */
@Configuration
public class UploadRootConfig implements WebMvcConfigurer {

    /**
     * 添加资源处理程序
     * addResourceHandler注册项目名称
     * addResourceLocations指定访问的本地路径
     *
     * @param registry 注册表
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image_upload/**")
                .addResourceLocations("file:D:\\image_upload\\");
    }
}
