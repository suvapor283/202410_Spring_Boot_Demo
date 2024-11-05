package com.example.basic.domain.admin.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    // 메인 (main)
    @GetMapping("/main")
    public String main(HttpSession session) {

        return "admin/main";
    }

    // 통계 (stat)
    @GetMapping("/stat")
    public String stat(HttpSession session) {

        return "admin/stat";
    }

    // 유저관리 (user)
    @GetMapping("/user")
    public String user(HttpSession session) {

        return "admin/stat";
    }
}
// 23부터