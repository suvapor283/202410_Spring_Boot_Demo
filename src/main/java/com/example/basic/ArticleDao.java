package com.example.basic;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleDao {

    void save(@Param("title") String title, @Param("body") String body);
//    List<Article> findAll();
    Article findById(Long id);
}
