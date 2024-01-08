package com.mcb.iminitializr.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean registWapperRequestFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new WapperRequestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("WapperRequestFilter");
        registration.setOrder(1);
        return registration;
    }
}
