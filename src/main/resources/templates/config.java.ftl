package ${packageName}.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ${packageName}.interceptor.${interceptorName};
import ${packageName}.filter.${filterName};

/**
* <p>
* WebMvc配置类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Configuration
public class ${className} implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ${interceptorName}()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean registWapperRequestFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ${filterName}());
        registration.addUrlPatterns("/*");
        registration.setName("${filterName}");
        registration.setOrder(1);
        return registration;
    }
}
