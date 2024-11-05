package com.example.basic.domain.auth.service;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getLoginMember(String username) {

        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        if (memberOptional.isPresent()) {
            return memberOptional.get();
        }

        return null;
    }
}