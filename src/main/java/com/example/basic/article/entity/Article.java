package com.example.basic.article.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    private Long id;
    private String title;
    private String body;
}