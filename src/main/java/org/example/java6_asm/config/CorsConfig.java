package org.example.java6_asm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Địa chỉ của ứng dụng React, không có khoảng trắng và không có dấu gạch chéo ở cuối
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true) // Cho phép gửi cookie
                .allowedHeaders("*");
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

