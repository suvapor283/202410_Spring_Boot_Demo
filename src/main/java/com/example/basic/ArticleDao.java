package com.example.basic;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleDao {

    @Insert("""
            INSERT INTO article
                SET title = #{title}
                    , `body` = #{body};
            """)
    void save(String title, String body);
}
