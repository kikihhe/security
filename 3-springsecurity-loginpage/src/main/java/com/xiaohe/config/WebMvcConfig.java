package com.xiaohe.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-03 11:33
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 设置静态资源映射，让他映射到resources目录下。


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/view/**").addResourceLocations("classpath:/view/");
    }
}
