package com.example.basic.domain.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {

    private long articleId;
    @NotBlank
    private String body;
}
