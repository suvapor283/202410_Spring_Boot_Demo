package com.example.basic.domain.auth.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {

    @Bean
    public FilterRegistrationBean<TestFilter> testFilterRegistrationBean() {
        FilterRegistrationBean<TestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TestFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}