package com.example.basic;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleDao {

    @Insert("""
            INSERT INTO article
                SET title = #{title}
                    , `body` = #{body}
            """)
    void save(String title, String body);

    @Select("""
            SELECT * FROM article
            """)
    List<Article> findAll();

    @Select("""
            SELECT * FROM article
                WHERE id = #{id}
            """)
    Article findById(Long id);
}
