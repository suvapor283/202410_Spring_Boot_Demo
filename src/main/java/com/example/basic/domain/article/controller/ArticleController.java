package com.example.basic.domain.article.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ReqResHandler reqResHandler;

    // list
    @RequestMapping("/article/list")
    public String list(Model model, HttpServletRequest request, HttpSession session) {
        List<Article> articleList = articleService.getAll();

        String username = (String) session.getAttribute("loginUser");

        if(username != null){
            model.addAttribute("loginedUser", username);
        }

        model.addAttribute("articleList", articleList);

        return "article/list";
    }

    // detail
    @RequestMapping("/article/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model, HttpServletRequest request) {
        Cookie targetCookie = reqResHandler.getCookieByName(request, "loginUser");

        if (targetCookie != null) {
            model.addAttribute("loginedUser", targetCookie.getValue());
            Cookie role = reqResHandler.getCookieByName(request, "role");
            model.addAttribute("role", role.getValue());
        }

        Article article = articleService.getById(id);
        model.addAttribute("article", article);

        return "article/detail";
    }

    // write
    @GetMapping("/article/write")
    public String articleWrite(Model model, HttpServletRequest request) {
        Cookie targetCookie = reqResHandler.getCookieByName(request, "loginUser");

        if (targetCookie != null) {
            model.addAttribute("loginedUser", targetCookie.getValue());
            Cookie role = reqResHandler.getCookieByName(request, "role");
            model.addAttribute("role", role.getValue());
        }

        return "article/write";
    }

    @Getter
    @Setter
    public static class WriteForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @PostMapping("/article/write")
    public String write(@Valid WriteForm writeForm, Model model) {
        articleService.write(writeForm.title, writeForm.body);

        return "redirect:/article/list";
    }

    // modify
    @Getter
    @Setter
    public static class ModifyForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @RequestMapping("/article/modify/{id}")
    public String modify(@PathVariable("id") long id, @Valid ModifyForm modifyForm) {
        articleService.update(id, modifyForm.getTitle(), modifyForm.getBody());

        return "redirect:/article/detail/%d".formatted(id);
    }

    // delete
    @RequestMapping("/article/delete/{id}")
    public String delete(@PathVariable long id) {
        articleService.deleteById(id);

        return "redirect:/article/list";
    }
}