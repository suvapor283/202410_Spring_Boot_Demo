package com.example.basic.domain.comment.comtroller;

import com.example.basic.domain.comment.CommentForm;
import com.example.basic.domain.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/comment")
@Controller
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @GetMapping("/write")
    public String write() {

        return "comment/write";
    }

    @PostMapping("/write")
    public String write(CommentForm commentForm) {
        commentService.write(commentForm.getBody(), commentForm.getArticleId());

        return "redirect:/article/detail/%d".formatted(commentForm.getArticleId());
    }

    // 댓글 수정
    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") long id, CommentForm commentForm) {
        commentService.update(id, commentForm.getBody());

        return "redirect:/article/detail/%d".formatted(commentForm.getArticleId());
    }

    // 댓글 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, CommentForm commentForm) {
        commentService.deleteById(id);

        return "redirect:/article/detail/%d".formatted(commentForm.getArticleId());
    }
}