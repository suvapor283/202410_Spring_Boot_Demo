package com.example.basic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
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
public class ArticleController {

    private final ArticleDao articleDao;

    // list
    @RequestMapping("/article/list")
    public String list(Model model) {
        List<Article> articleList = articleDao.findAll();
        model.addAttribute("articleList", articleList);

        return "article/list";
    }

    // detail
    @RequestMapping("/article/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        Article article = articleDao.findById(id);
        model.addAttribute("article", article);

        return "article/detail";
    }

    // write
    @Getter
    public static class WriteForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @GetMapping("/article/write")
    public String write() {
        return "article/write";
    }

    @PostMapping("/article/write")
    public String write(@Valid WriteForm writeForm) {
        Article article = Article.builder()
                .title(writeForm.getTitle())
                .body(writeForm.getBody())
                .build();

        articleDao.save(article);

        return "redirect:/article/list";
    }

    // modify
    @Getter
    public static class modifyForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @RequestMapping("/article/modify/{id}")
    public String update(@PathVariable long id, @Valid modifyForm modifyForm) {
        Article article = Article.builder()
                .id(id)
                .title(modifyForm.getTitle())
                .body(modifyForm.getBody())
                .build();

        articleDao.update(article);

        return "redirect:/article/detail/%d".formatted(id);
    }

    // delete
    @RequestMapping("/article/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        articleDao.deleteById(id);

        return "redirect:/article/list";
    }
}