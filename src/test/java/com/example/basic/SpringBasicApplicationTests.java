package com.example.basic;

import com.example.basic.domain.auth.dao.MemberDao;
import com.example.basic.domain.auth.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBasicApplicationTests {

    @Autowired
    private MemberDao memberDao;

    @Test
    void t1() {
        Member member = Member.builder()
                .username("kim")
                .password("1234")
                .role("normal")
                .build();

        memberDao.save(member);
    }
}