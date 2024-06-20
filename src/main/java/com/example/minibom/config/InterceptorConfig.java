package com.example.minibom.config;

import com.example.minibom.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JWTInterceptor())
//                // 先不设置拦截
//                // .addPathPatterns("/**")
//                .excludePathPatterns("/feign/user/login", "/feign/user/register");
//    }
}
