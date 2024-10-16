package com.example.basic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

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
    public String login(@Valid loginForm loginForm, Model model) {
        String dbUser = "hong";
        String dbpass = "1234";

        if (!dbUser.equals(loginForm.username) || !dbpass.equals(loginForm.password)) {
            return "login-fail";
        }

        model.addAttribute("loginedUser", loginForm.username);

        return "redirect:/article/list";
    }
}
