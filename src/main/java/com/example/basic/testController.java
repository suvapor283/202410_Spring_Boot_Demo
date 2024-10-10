package com.example.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@Controller
public class testController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {

        return "test";
    }

    @RequestMapping("/t2")
    @ResponseBody
    public Integer t2(Integer num) {

        return num * 2;
    }

    @RequestMapping("/t3")
    @ResponseBody
    public Article t3() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("title1");
        article.setBody("body1");

        return article;
    }

    @RequestMapping("/t4")
    @ResponseBody
    public List<Article> t4() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("title1");
        article.setBody("body1");

        Article article2 = new Article();
        article2.setId(2L);
        article2.setTitle("title2");
        article2.setBody("body2");

        List<Article> articleList = new ArrayList<>() {{
            add(article);
            add(article2);
        }};

        return articleList;
    }
}