package com.example.basic.domain.comment.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    public void write(String body, long articleId, Member author) {
        Article article = articleService.getById(articleId);

        Comment comment = new Comment();
        comment.setBody(body);
        comment.setArticle(article);
        comment.setAuthor(author);

        commentRepository.save(comment);
    }

    public void update(long id, String body) {
        Comment comment = this.getById(id);
        comment.setBody(body);

        commentRepository.save(comment);
    }

    public void deleteById(long id) {
        Comment comment = getById(id);

        commentRepository.delete(comment);
    }

    public Comment getById(long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);

        if (commentOptional.isEmpty()) {
            throw new RuntimeException("없는 데이터입니다.");
        }

        return commentOptional.get();
    }

    public List<Comment> getCommentsByAuthor(Member author) {

        return commentRepository.findByAuthorOrderByIdDesc(author);
    }
}