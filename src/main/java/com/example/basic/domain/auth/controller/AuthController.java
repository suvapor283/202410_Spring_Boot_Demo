package com.example.basic.domain.auth.controller;

import com.example.basic.domain.auth.entity.Member;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {

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
    public String login(@Valid loginForm loginForm, HttpSession session) {
        List<Member> memberList = new ArrayList<>();

        Member member1 = Member.builder()
                .username("hong")
                .password("1234")
                .role("admin")
                .build();

        Member member2 = Member.builder()
                .username("kim")
                .password("1234")
                .role("normal")
                .build();

        memberList.add(member1);
        memberList.add(member2);

        Member targetMember = null;

        for (Member member : memberList) {
            if (member.getUsername().equals(loginForm.username) && member.getPassword().equals(loginForm.password)) {
                targetMember = member;
                break;
            }
        }

        if (targetMember == null) {
            return "login-fail";
        }

        session.setAttribute("loginUser", loginForm.username);
        session.setAttribute("role", targetMember.getRole());

        return "redirect:/article/list";
    }

    // 로그아웃 (logout)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/article/list";
    }
}