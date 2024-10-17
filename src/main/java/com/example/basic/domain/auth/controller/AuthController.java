package com.example.basic.domain.auth.controller;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    private final ReqResHandler reqResHandler;

    // login
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
    public String login(@Valid loginForm loginForm, HttpServletResponse response) {
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

        Cookie cookie = new Cookie("loginUser", loginForm.username);
        Cookie role = new Cookie("role", targetMember.getRole());

        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);
        response.addCookie(role);

        return "redirect:/article/list";
    }

    // logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie targetCookie = reqResHandler.getCookieByName(request, "loginUser");

        if (targetCookie != null) {
            targetCookie.setMaxAge(0);
            response.addCookie(targetCookie);
        }

        return "redirect:/article/list";
    }
}
