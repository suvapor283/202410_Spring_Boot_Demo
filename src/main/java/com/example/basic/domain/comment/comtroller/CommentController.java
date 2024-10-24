package com.example.basic.domain.comment.comtroller;

import com.example.basic.domain.comment.service.CommentService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/comment")
@Controller
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/write")
    public String write(String body, long articleId) {
        commentService.write(body, articleId);

        return "redirect:/article/detail/%d".formatted(articleId);
    }

    @Getter
    @Setter
    public static class ModifyForm {
        private long articleId;
        @NotBlank
        private String body;
    }

    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") long id, ModifyForm modifyForm) {
        commentService.update(id, modifyForm.body);

        return "redirect:/article/detail/%d".formatted(modifyForm.articleId);
    }
}