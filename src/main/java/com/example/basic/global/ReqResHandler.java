package com.example.basic.global;

import com.example.basic.domain.auth.entity.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReqResHandler {

    private final HttpSession session;
    private final String LOGIN_USER_KEY = "loginMember";

    public void setLoginMember(Member member) {
        session.setAttribute(LOGIN_USER_KEY, member);
    }

    public Member getLoginMember() {
        return (Member) session.getAttribute(LOGIN_USER_KEY);
    }
}