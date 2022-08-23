package dev.dmchoi.eomisae.configs;

import dev.dmchoi.eomisae.interceptors.SystemInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.systemInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/banned")
                .excludePathPatterns("/resources/**");
    }
    @Bean
    public SystemInterceptor systemInterceptor() {
        return new SystemInterceptor();
    }
}
