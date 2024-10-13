package com.example.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/article/write")
    public String write() {
        return "article/write";
    }

    @PostMapping("/article/write")
    @ResponseBody
    public String write(String title, String body) {
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

        articleDao.save(article);

        return "write 성공";
    }

    // delete
    @RequestMapping("/article/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") long id) {
        articleDao.deleteById(id);

        return "delete 성공";
    }

    // modify
    @RequestMapping("/article/modify/{id}")
    @ResponseBody
    public String update(@PathVariable long id, String title, String body) {
        Article article = Article.builder()
                .id(id)
                .title(title)
                .body(body)
                .build();

        articleDao.update(article);

        return "update 성공";
    }
}