package org.zerock.api01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 파라미터 타입 변환을 추가할 수 있도록 함
 */
@Configuration
@EnableWebMvc
public class CustomServletConfig implements WebMvcConfigurer {

    /**
     * 중간에 '/files/'로 시작하는 경로는 스프링 MVC에서 일반 파일 경로로 처리하도록 지정해서 사용하도록 구성
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("classPath:/static/");
    }
}
