package com.example.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 스프링 설정 클래스임을 명시
public class InterceptorConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")               // 모든 경로에 인터셉터 적용
                .excludePathPatterns(                 // 제외할 경로들
                    "/login",                         // 로그인 페이지
                    "/register",                      // 회원가입 페이지
                    "/css/**",                        // CSS 리소스
                    "/js/**",                         // JavaScript 리소스
                    "/images/**",                     // 이미지 리소스
                    "/error/**"                       // 에러 페이지
                );
    }
}