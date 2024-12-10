package com.example.basic;

import com.example.basic.test.Account;
import com.example.basic.test.AccountRepository;
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

    @Test
    void t1() {
        System.out.println("test1");
    }

    @Test
    @DisplayName("계좌 테스트 데이터 생성")
    void t2(){
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
    void t3(){

    }

    @Test
    @DisplayName("@Transactional 설정한 경우")
    void t4(){

    }
}