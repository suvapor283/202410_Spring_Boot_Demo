package com.example.basic.domain.article.controller;

import com.example.basic.domain.article.ArticleForm;
import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.member.entity.Member;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    // 작성 (write)
    @GetMapping("/write")
    public String write() {

        return "article/write";
    }

    @PostMapping("/write")
    public String write(@Valid ArticleForm articleForm, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");

        articleService.write(articleForm.getTitle(), articleForm.getBody(), loginMember);

        return "redirect:/article/list";
    }

    // 전체 조회 (list)
    @GetMapping("/list")
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

    // 수정 (modify)
    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable("id") long id, @Valid ArticleForm articleForm) {
        articleService.update(id, articleForm.getTitle(), articleForm.getBody());

        return "redirect:/article/detail/%d".formatted(id);
    }

    // 삭제 (delete)
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        articleService.deleteById(id);

        return "redirect:/article/list";
    }
}