package com.example.basic.domain.login.controller;

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

@Controller
@RequiredArgsConstructor
public class LoginController {

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
        String dbUser = "hong";
        String dbPass = "1234";
        String dbRole = "admin";

        if (!dbUser.equals(loginForm.username) || !dbPass.equals(loginForm.password)) {
            return "login-fail";
        }

        Cookie cookie = new Cookie("loginUser", loginForm.username);
        Cookie role = new Cookie("role", dbRole);

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
