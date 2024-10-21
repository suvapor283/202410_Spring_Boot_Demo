package com.example.basic.domain.auth.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {

    // 관리자 권한 체크 (admin filter)
    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilterRegistrationBean() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/admin/*");

        return registrationBean;
    }

    // 로그인 권한 체크 (login filter)
    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterRegistrationBean() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/article/write", "/article/delete/*", "/article/modify/*");

        return registrationBean;
    }
}