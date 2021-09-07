package com.matchmaking.backend;

import com.google.code.kaptcha.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    Producer producer;
    @Test
    void contextLoads() {
        String code = producer.createText();
        System.out.println(code);
    }




}
