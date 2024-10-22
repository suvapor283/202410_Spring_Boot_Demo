package com.example.basic.domain.auth.filter;

import com.example.basic.global.ReqResHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MyFilterConfig {

    private final ReqResHandler reqResHandler;

    // 관리자 권한 체크 (admin filter)
    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilterRegistrationBean() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter(reqResHandler));
        registrationBean.addUrlPatterns("/admin/*");

        return registrationBean;
    }

    // 로그인 권한 체크 (login filter)
    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterRegistrationBean() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter(reqResHandler));
        registrationBean.addUrlPatterns("/article/write", "/article/delete/*", "/article/modify/*");

        return registrationBean;
    }
}