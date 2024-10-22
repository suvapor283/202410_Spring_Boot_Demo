package com.example.basic.domain.auth.filter;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.global.reqres.ReqResHandler;
import jakarta.servlet.*;

import java.io.IOException;

public class AdminFilter implements Filter {

    private ReqResHandler reqResHandler;

    public AdminFilter(ReqResHandler reqResHandler) {
        this.reqResHandler = reqResHandler;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Member loginMember = reqResHandler.getLoginMember();

        if (loginMember == null) {
            throw new RuntimeException("관리자 계정으로 로그인 해야만 사용 가능합니다.");
        }

        if (!loginMember.getRole().equals("admin")) {
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