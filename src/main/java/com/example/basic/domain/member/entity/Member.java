package com.example.basic.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    public String switchKoreanRole() {
        switch (this.role) {
            case "admin":
                return "관리자";
            case "normal":
                return "일반회원";

        }

        throw new RuntimeException("없는 권한 정보입니다.");
    }
}