package com.example.basic.domain.auth.controller;

import com.example.basic.domain.auth.service.AuthService;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.global.reqres.ReqResHandler;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ReqResHandler reqResHandler;

    // 로그인 (login)
    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @Getter
    @Setter
    public static class loginForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/login")
    public String login(@Valid loginForm loginForm) {

        Member targetMember = authService.getLoginMember(loginForm.username, loginForm.password);

        reqResHandler.setLoginMember(targetMember);

        return "redirect:/article/list";
    }

    // 로그아웃 (logout)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/article/list";
    }
}