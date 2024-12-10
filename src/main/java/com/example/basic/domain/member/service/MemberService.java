package com.example.basic.domain.member.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.service.CommentService;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ArticleService articleService;
    private final CommentService commentService;

    public Member getByUsernameOrNull(String username) {

        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        if (memberOptional.isEmpty()) {
            return null;
        }

        return memberOptional.get();
    }

    public List<Article> getArticlesByAuthor(Member author) {

        return articleService.getArticlesByAuthor(author);
    }

    public List<Comment> getCommentsByAuthor(Member author) {

        return commentService.getCommentsByAuthor(author);
    }
}