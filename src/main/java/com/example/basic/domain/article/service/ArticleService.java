package com.example.basic.domain.article.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAll() {

        return articleRepository.findAll();
    }

    public Article getById(long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (articleOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 게시물입니다.");
        }

        return articleOptional.get();
    }

    public void write(String title, String body, Member author) {
        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);
        article.setAuthor(author);

        articleRepository.save(article);
    }

    public void update(long id, String title, String body) {
        Article article = this.getById(id);
        article.setTitle(title);
        article.setBody(body);

        articleRepository.save(article);
    }

    public void deleteById(long id) {
        Article article = getById(id);

        articleRepository.delete(article);
    }

    public List<Article> getArticlesByAuthor(Member author) {

        return articleRepository.findByAuthor(author);
    }
}