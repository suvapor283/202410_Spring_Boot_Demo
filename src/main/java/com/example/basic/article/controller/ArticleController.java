package com.example.basic.article.controller;

import com.example.basic.article.entity.Article;
import com.example.basic.article.service.ArticleService;
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

    // list
    @RequestMapping("/article/list")
    public String list(Model model) {
        List<Article> articleList = articleService.getAll();
        model.addAttribute("articleList", articleList);
        model.addAttribute("loginedUser", "hong");

        return "article/list";
    }

    // detail
    @RequestMapping("/article/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        Article article = articleService.getById(id);
        model.addAttribute("article", article);

        return "article/detail";
    }

    // write
    @GetMapping("/article/write")
    public String articleWrite() {
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