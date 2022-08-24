package dev.dmchoi.eomisae.configs;

import dev.dmchoi.eomisae.interceptors.SessionInterceptor;
import dev.dmchoi.eomisae.interceptors.SystemInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.systemInterceptor())
                .addPathPatterns("/**") // 작동 경로
                .excludePathPatterns("/banned") // 예외 경로
                .excludePathPatterns("/resources/**");
        registry.addInterceptor(this.sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**");
    }
    @Bean
    public SystemInterceptor systemInterceptor() {
        return new SystemInterceptor();
    }

    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(false);
        // user/login (o)  login이라는 파일이 있는거고
        // user/login/(x)  login이라는 디렉토리가 있는 것
    }
}
