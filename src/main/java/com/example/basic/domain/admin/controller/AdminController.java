package com.example.basic.domain.admin.controller;

import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {

    private final ReqResHandler reqResHandler;

    @GetMapping("/admin/main")
    public String main(HttpServletRequest request, Model model) {
        Cookie targetCookie = reqResHandler.getLoginCookie(request);

        if (targetCookie == null) {
            throw new RuntimeException("관리자 계정으로 로그인 해야만 사용 가능합니다.");
        }

        model.addAttribute("loginedUser", targetCookie.getValue());

        return "admin/main";
    }
}
