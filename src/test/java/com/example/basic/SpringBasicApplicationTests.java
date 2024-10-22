package com.example.basic;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringBasicApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("회원 저장 - save")
    void t1() {
        Member member = new Member();
        member.setUsername("hong");
        member.setPassword("1234");
        member.setRole("admin");

        memberRepository.save(member);

        Member member2 = new Member();
        member2.setUsername("kim");
        member2.setPassword("1234");
        member2.setRole("normal");

        memberRepository.save(member2);
    }

    @Test
    @DisplayName("회원 전체 조회 - findAll")
    void t2() {
        List<Member> memberList = memberRepository.findAll();

        for (Member member : memberList) {
            System.out.println(member.getUsername());
        }
    }

    @Test
    @DisplayName("회원 단건 조회 - findById")
    void t3() {
        Optional<Member> memberOptional = memberRepository.findById(1L);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            System.out.println(member.getUsername());
            System.out.println(member.getPassword());
            System.out.println(member.getRole());
        }
    }

    @Test
    @DisplayName("회원 삭제 - delete, deleteById")
    void t4() {
        // delete
        Optional<Member> memberOptional = memberRepository.findById(1L);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            memberRepository.delete(member);
        }

        // deleteById
        memberRepository.deleteById(1L);
    }

    @Test
    @DisplayName("회원 수정 - save")
    void t5() {
        Optional<Member> memberOptional = memberRepository.findById(1L);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            member.setUsername("lee");
            member.setPassword("4321");
            member.setRole("admin");

            memberRepository.save(member);
        }
    }

    @Test
    @DisplayName("Article에 외래키로 memberId 넣어서 저장")
    void t6() {
        Member member = new Member();
        member.setUsername("kim");
        member.setPassword("1234");
        member.setRole("admin");

        Article article = new Article();
        article.setTitle("테스트 제목1");
        article.setBody("테스트 내용1");
        article.setMemberId(1L);

        memberRepository.save(member);
        articleRepository.save(article);
    }

    @Test
    @DisplayName("게시물 정보와 작성자 정보 같이 가져오기")
    void t7() {
        Article article = articleRepository.findById(1L).get();

        long memberId = article.getMemberId();

        System.out.println(memberRepository.findById(memberId));
    }
}