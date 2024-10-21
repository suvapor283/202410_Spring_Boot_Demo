package com.example.basic.domain.article.dao;

import com.example.basic.domain.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {

    void save(Article article);

    List<Article> findAll();

    Article findById(Long id);

    void deleteById(Long id);

    void update(Article article);
}