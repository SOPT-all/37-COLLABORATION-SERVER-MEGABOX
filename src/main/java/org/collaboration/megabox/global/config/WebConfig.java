package org.collaboration.megabox.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS 적용
            .allowedOriginPatterns("*") // Origin이 "*"일 때는 credentials false가 기본값 → true로
            .allowedMethods("*") // 모든 HTTP 메서드 허용
            .allowedHeaders("*")  // 모든 헤더 허용
            .allowCredentials(true); // 인증 정보 포함한 요청(쿠키, Authorization 헤더) 허용
    }
}