package com.example.basic;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleDao {

    void save(@Param("title") String title, @Param("body") String body);
    List<Article> findAll();
    Article findById(Long id);
    void deleteById(Long id);
    //void update(@Param("id") Long id, @Param("title") String title, @Param("body") String body);
    void update(Article article);
}
