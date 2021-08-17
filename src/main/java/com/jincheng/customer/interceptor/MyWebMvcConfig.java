package com.jincheng.customer.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    TestInterceptor testInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(testInterceptor);
        //"/**"包含所有的请求：“/*”代表包含一级目录
        interceptorRegistration.addPathPatterns("/**");
        //   static/js/a.js;b.js  "../"表示上级目录
        interceptorRegistration.excludePathPatterns("/js/**", "/css/**", "/fonts/**", "/images/**", "/**.html", "/page");
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/image/**").addResourceLocations("file:D:/专业课/CMS/images/product/");
//    }
}
