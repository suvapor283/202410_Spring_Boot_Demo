package com.example.basic.domain.comment.entity;

import com.example.basic.domain.article.entity.Article;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String body;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}