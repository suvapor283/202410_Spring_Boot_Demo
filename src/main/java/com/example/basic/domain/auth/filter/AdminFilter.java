package com.example.basic.domain.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("loginUser");

        if (username == null) {
            throw new RuntimeException("관리자 계정으로 로그인 해야만 사용 가능합니다.");
        }

        String role = (String) session.getAttribute("role");

        if (!role.equals("admin")) {
            throw new RuntimeException("관리자 권한만 접근 가능합니다.");
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