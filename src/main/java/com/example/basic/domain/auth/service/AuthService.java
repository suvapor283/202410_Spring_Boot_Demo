package com.example.basic.domain.auth.service;

import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;

    public Member getLoginMember(String username) {

        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        if (memberOptional.isEmpty()) {
            throw new RuntimeException("잘못된 회원정보입니다.");
        }

        return memberOptional.get();
    }
}
