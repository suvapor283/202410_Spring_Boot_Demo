package com.example.basic;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBasicApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
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
    void t2() {
        List<Member> memberList = memberRepository.findAll();

        for (Member member : memberList) {
            System.out.println(member.getUsername());
        }
    }
}