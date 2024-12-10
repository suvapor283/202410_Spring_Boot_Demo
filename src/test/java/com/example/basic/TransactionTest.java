package com.example.basic;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.repository.MemberRepository;
import com.example.basic.test.Account;
import com.example.basic.test.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void t1() {
        System.out.println("test1");
    }

    @Test
    @DisplayName("계좌 테스트 데이터 생성")
    void t2() {
        Account a1 = new Account();
        a1.setOwner("홍길동");
        a1.setAmount(20000);

        Account a2 = new Account();
        a2.setOwner("임꺽정");
        a2.setAmount(10000);

        accountRepository.save(a1);
        accountRepository.save(a2);
    }

    @Test
    @DisplayName("@Transactional 설정하지 않는 경우")
    void t3() {
        Account a1 = accountRepository.findById(1L).get();
        Account a2 = accountRepository.findById(2L).get();

        // 2개의 DB 작업은 모두 성공 or 모두 실패 => 트랜잭션
        a1.setAmount(a1.getAmount() - 1000);
        accountRepository.save(a1);

        if (true) {
            throw new RuntimeException("강제로 예외 발생");
        }

        a2.setAmount(a2.getAmount() + 1000);
        accountRepository.save(a2);
    }

    @Test
    @DisplayName("@Transactional 설정한 경우")
    @Transactional
    void t4() {
        Account a1 = accountRepository.findById(1L).get();
        Account a2 = accountRepository.findById(2L).get();

        a1.setAmount(a1.getAmount() - 1000);
        accountRepository.save(a1);

        if (true) {
            throw new RuntimeException("강제로 예외 발생");
        }

        a2.setAmount(a2.getAmount() + 1000);
        accountRepository.save(a2);
    }

    private Article makeTestArticle(String title, String body, Member author) {
        return Article.builder()
                .title(title)
                .body(body)
                .author(author)
                .build();
    }

    private Member makeTestMember(String username, String password, String role) {
        return Member.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();
    }

    @Test
    @DisplayName("회원 & 게시물 테스트 데이터 생성")
    void t5() {
        System.out.println("====== 테스트 데이터 생성 시작 ======");

        Member m1 = makeTestMember("hong", "1234", "admin");
        Member m2 = makeTestMember("kim", "qwer", "normal");

        memberRepository.save(m1);
        memberRepository.save(m2);

        Article a1 = makeTestArticle("제목1", "내용1", m1);
        Article a2 = makeTestArticle("제목2", "내용2", m1);
        Article a3 = makeTestArticle("제목3", "내용3", m2);

        articleRepository.save(a1);
        articleRepository.save(a2);
        articleRepository.save(a3);

        System.out.println(" ====== 테스트 데이터 등록 완료 ======");
    }

    @Test
    @DisplayName("Lazy 로딩")
    @Transactional
    void t6() {
        Member m1 = memberRepository.findById(1L).get();

//        List<Article> articles = m1.getArticles();

//        for (Article article : articles) {
//            System.out.println(article.getTitle());
//        }
    }
}