package com.example.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleDao articleDao;

    @RequestMapping("/article/detail/{id}")
    @ResponseBody
    public Article detail(@PathVariable("id") long id) {
        Article article = articleDao.findById(id);

        return article;
    }

    @RequestMapping("/article/list")
    @ResponseBody
    public List<Article> list() {
        List<Article> articleList = articleDao.findAll();

        return articleList;
    }

    @RequestMapping("/article/write")
    @ResponseBody
    public String write(String title, String body) {
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

        articleDao.save(article);

        return "write 성공";
    }

    @RequestMapping("/article/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") long id) {
        articleDao.deleteById(id);

        return "delete 성공";
    }

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

    @RequestMapping("/show-html")
    public String showHtml() {
        return "test";
    }

    @RequestMapping("/article-write")
    public String write(){
        return "article/write";
    }
}