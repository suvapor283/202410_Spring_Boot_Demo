package com.example.basic;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.repository.MemberRepository;
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

            member.setUsername("kim");
            member.setPassword("1234");
            member.setRole("admin");

            memberRepository.save(member);
        }
    }

    @Test
    @DisplayName("JPA 연관관계를 이용한 데이터 저장")
    void t6() {
        Member m1 = new Member();
        m1.setUsername("hong");
        m1.setPassword("1234");
        m1.setRole("admin");

        memberRepository.save(m1);

        Article a1 = new Article();
        a1.setTitle("테스트 제목1");
        a1.setBody("테스트 내용1");
        a1.setAuthor(m1);

        articleRepository.save(a1);
    }

    @Test
    @DisplayName("JPA 연관관계를 이용한 데이터 조회")
    void t7() {
        Article article = articleRepository.findById(1L).get();

        System.out.println(article.getTitle());
        System.out.println(article.getAuthor().getUsername());
    }
}