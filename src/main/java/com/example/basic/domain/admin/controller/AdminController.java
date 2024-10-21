package com.example.basic.domain.admin.controller;

import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ReqResHandler reqResHandler;

    // 메인 (main)
    @GetMapping("/main")
    public String main(HttpSession session) {
        String username = (String) session.getAttribute("loginUser");

        if (username == null) {
            throw new RuntimeException("관리자 계정으로 로그인 해야만 사용 가능합니다.");
        }

        String role = (String) session.getAttribute("role");

        if (!role.equals("admin")) {
            throw new RuntimeException("관리자 권한만 접근 가능합니다.");
        }

        return "admin/main";
    }

    // 통계 (stat)
    @GetMapping("/stat")
    public String stat(HttpSession session) {
        String username = (String) session.getAttribute("loginUser");

        if (username == null) {
            throw new RuntimeException("관리자 계정으로 로그인 해야만 사용 가능합니다.");
        }

        String role = (String) session.getAttribute("role");

        if (!role.equals("admin")) {
            throw new RuntimeException("관리자 권한만 접근 가능합니다.");
        }
        return "admin/stat";
    }

    // 유저관리 (user)
    @GetMapping("/user")
    public String user(HttpSession session) {
        String username = (String) session.getAttribute("loginUser");

        if (username == null) {
            throw new RuntimeException("관리자 계정으로 로그인 해야만 사용 가능합니다.");
        }

        String role = (String) session.getAttribute("role");

        if (!role.equals("admin")) {
            throw new RuntimeException("관리자 권한만 접근 가능합니다.");
        }

        return "admin/stat";
    }
}