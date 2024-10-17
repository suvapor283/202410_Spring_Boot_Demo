package com.example.basic.domain.admin.controller;

import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {

    private final ReqResHandler reqResHandler;

    @GetMapping("/admin/main")
    public String main(HttpSession session) {
        String username = (String) session.getAttribute("loginUser");

        if (username == null) {
            throw new RuntimeException("관리자 계정으로 로그인 해야만 사용 가능합니다.");
        }

        String role = (String) session.getAttribute("role");

        if(!role.equals("admin")){
            throw new RuntimeException("관리자 권한만 접근 가능합니다.");
        }

        return "admin/main";
    }
}
