package com.example.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final ArticleDao articleDao;

    @RequestMapping("/article/detail/{id}")
    @ResponseBody
    public Article detail(@PathVariable("id") long id){
        Article article = articleDao.findById(id);

        return article;
    }

    @RequestMapping("/article/list")
    @ResponseBody
    public List<Article> list(){
        List<Article> articleList = articleDao.findAll();

        return articleList;
    }

    @RequestMapping("/article/write")
    @ResponseBody
    public String write(String title, String body){
        articleDao.save(title, body);

        return "성공";
    }
}