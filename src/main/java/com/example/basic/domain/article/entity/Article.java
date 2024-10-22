package com.example.basic.domain.article.entity;

import com.example.basic.domain.auth.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member author;
}