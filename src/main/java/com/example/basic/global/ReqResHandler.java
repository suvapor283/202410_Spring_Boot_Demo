package com.example.basic.global;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class ReqResHandler {

    // 쿠키 전송 함수
    public Cookie getLoginCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie targetCookie = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("loginUser".equals(cookie.getName())) {
                    targetCookie = cookie;
                }
            }
        }

        return targetCookie;
    }
}
