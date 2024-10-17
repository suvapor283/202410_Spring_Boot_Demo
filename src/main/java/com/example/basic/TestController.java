package com.example.basic;

import com.example.basic.domain.article.dao.ArticleDao;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final ArticleDao articleDao;

    @GetMapping("/cookie-test")
    @ResponseBody
    public String cookieTest(HttpServletResponse response) {
        Cookie cookie = new Cookie("test", "1234");

        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "쿠키를 발행하였습니다.";
    }

    @GetMapping("/find-cookie")
    @ResponseBody
    public String findCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("test".equals(cookie.getName())) {
                    return "Cookie value : " + cookie.getValue();
                }
            }
        }

        return "쿠키가 없습니다.";
    }
}
