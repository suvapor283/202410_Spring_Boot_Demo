package com.example.basic.domain.member.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.service.MemberService;
import com.example.basic.global.reqres.ReqResHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final ReqResHandler reqResHandler;
    private final MemberService memberService;

    @GetMapping("/info")
    public String info(Model model) {
        Member loginMember = reqResHandler.getLoginMember();

        List<Article> articleList = memberService.getArticlesByAuthor(loginMember);
        List<Comment> commentList = memberService.getCommentsByAuthor(loginMember);

        model.addAttribute("articleList", articleList);
        model.addAttribute("commentList", commentList);

        return "member/info";
    }
}