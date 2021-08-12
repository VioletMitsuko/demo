package com.example.system.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * @author VioletMitsuko
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * 添加注册器
     * @param registry  .
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
    }

    /**
     * 将权限拦截器注入spring
     * @return .
     */
    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

}
