package dev.dmchoi.eomisae.configs;

import dev.dmchoi.eomisae.interceptors.SessionInterceptor;
import dev.dmchoi.eomisae.interceptors.SystemInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//인터셉터는 특정 주소 패턴에 대해 광범위하게 작동.
//모든 주소에 대해 작동하지 않기 때문에 직접 등록시켜준다.
//톰캣이 구동될 때 한번 작동한다.
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.systemInterceptor())
                .addPathPatterns("/**") // 작동 경로 설정 -> 모든 경우에 대해 ip 체크
                .excludePathPatterns("/banned") // 예외 경로
                .excludePathPatterns("/resources/**");
                // 시스템 과부화를 막기 위해 resources로 시작하는 모든 경로도 다 예외 처리
                // css html js 전부 체크하게 되면 페이지 로드 속도가 너무 떨어지기 때문.
        registry.addInterceptor(this.sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/banned")
                .excludePathPatterns("/resources/**");
    }

//  내가 직접 객체화 하는게 아니라 스프링이 객체화하여 주어야 한다라는 의미를 부여하기 위해서는
//  메서드 위에 @Bean 어노테이션을 붙여주면 된다
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
