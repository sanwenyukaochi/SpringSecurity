package com.springframework.security;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityApplicationTests {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    void test01() {
        String encode = passwordEncoder.encode("123456");
        IO.println(encode);
        boolean matches = passwordEncoder.matches("123456", encode);
        IO.println(matches);
    }

    @Test
    void test02() {
    }

    @Test
    void test03() {

    }

}
