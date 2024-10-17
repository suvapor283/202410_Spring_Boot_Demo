package com.example.basic.domain.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {

    @GetMapping("/admin/main")
    public String main(){
        return "admin/main";
    }
}
