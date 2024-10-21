package com.example.basic.domain.article.service;

import com.example.basic.domain.article.dao.ArticleDao;
import com.example.basic.domain.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleDao articleDao;

    public List<Article> getAll() {
        return articleDao.findAll();
    }

    public Article getById(long id) {
        return articleDao.findById(id);
    }

    public void write(String title, String body) {
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

        articleDao.save(article);
    }

    public void update(long id, String title, String body) {
        Article article = Article.builder()
                .id(id)
                .title(title)
                .body(body)
                .build();

        articleDao.update(article);
    }

    public void deleteById(long id) {
        articleDao.deleteById(id);
    }
}