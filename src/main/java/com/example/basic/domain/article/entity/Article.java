package com.example.basic.domain.article.entity;

import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private Member author;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    List<Comment> commentList = new ArrayList<>();
}