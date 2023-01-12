package com.summer.config.file;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件上传映射类
 * @author tubo
 * @date 2022/05/28
 */
@Configuration
public class SpringBootWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这样所有以file开头的请求都被视为请求上传的文件,映射到真正的路径上去
        //registry.addResourceHandler("/file/**").addResourceLocations("file:/images/");
        registry.addResourceHandler("/file/**").addResourceLocations("file:D:/images/");
    }
}