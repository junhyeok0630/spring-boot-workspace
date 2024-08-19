package com.example.ex28_security_db;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class Ex28SecurityDbApplicationTest {

    @Test
    public void contextLoads() {
        System.out.println("테스트!");
    }

    @Test
    public void testBCryptPasswordEncoder() {
        String rawPassword = "abcd";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Encoded password : " + encodedPassword);
    }
}
