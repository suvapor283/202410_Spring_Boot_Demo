package com.example.basic.domain.article;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleForm {

    @NotBlank
    private String title;
    @NotBlank
    private String body;
}