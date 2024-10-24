package com.example.basic.domain.comment.comtroller;

import com.example.basic.domain.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
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
}