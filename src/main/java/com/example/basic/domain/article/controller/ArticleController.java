package com.example.basic.domain.article.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
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
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    // 전체 조회 (list)
    @RequestMapping("/list")
    public String list(Model model) {
        List<Article> articleList = articleService.getAll();

        model.addAttribute("articleList", articleList);

        return "article/list";
    }

    // 단건 조회 (detail)
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model) {

        Article article = articleService.getById(id);
        model.addAttribute("article", article);

        return "article/detail";
    }

    // 작성 (write)
    @GetMapping("/write")
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

    @PostMapping("/write")
    public String write(@Valid WriteForm writeForm, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");

        articleService.write(writeForm.title, writeForm.body, loginMember);

        return "redirect:/article/list";
    }

    // 수정 (modify)
    @Getter
    @Setter
    public static class ModifyForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable("id") long id, @Valid ModifyForm modifyForm) {
        articleService.update(id, modifyForm.getTitle(), modifyForm.getBody());

        return "redirect:/article/detail/%d".formatted(id);
    }

    // 삭제 (delete)
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        articleService.deleteById(id);

        return "redirect:/article/list";
    }
}