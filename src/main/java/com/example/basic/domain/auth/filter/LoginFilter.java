package com.example.basic.domain.auth.filter;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.global.reqres.ReqResHandler;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginFilter implements Filter {

    private ReqResHandler reqResHandler;
    List<String> excludedUrls;

    public LoginFilter(ReqResHandler reqResHandler) {
        this.reqResHandler = reqResHandler;
        excludedUrls = new ArrayList<>(List.of(
                "/article/list",
                "/article/detail/",
                "/login",
                "/global.css"
        ));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String reqUrl = request.getRequestURI();

        for (String excludedUrl : excludedUrls) {
            if (reqUrl.startsWith(excludedUrl)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        if (excludedUrls.contains(reqUrl)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Member loginMember = reqResHandler.getLoginMember();

        if (loginMember == null) {
            HttpServletResponse res = (HttpServletResponse) servletResponse;
            res.sendRedirect("/login");

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}