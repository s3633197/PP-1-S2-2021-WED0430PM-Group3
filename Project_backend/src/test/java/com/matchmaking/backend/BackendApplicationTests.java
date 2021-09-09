package com.matchmaking.backend;

import com.google.code.kaptcha.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    Producer producer;

    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Test
    void contextLoads() {
        String code = producer.createText();
        System.out.println(code);
    }


    @Test
    void sendEmail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        helper.setSubject("Verify your email address");
        String content ="<div style=\"display: flex; flex-direction:column; justify-content:center; align-items:center; background-color:white; border-radius:10px;padding:0 10px;\">\n" +
                "    <h2>Welcome to Elite Net</h2>\n" +
                "    <div>\n" +
                "        <span>Register verification code</span>\n" +
                "    </div>\n" +
                "    <div style=\"border-radius: 8px; background-color:#f9f9f9; padding:10px; width:300px; margin:20px;\">\n" +
                "        <p style=\"text-align: center; font-size:3rem; color:#357ae8;font-weight:bolder;\">dasdasdas</p>\n" +
                "    </div>\n" +
                "</div>";
        helper.setText(content,true);
        helper.setFrom("kenlbd61@126.com");
        helper.setTo("764449793@qq.com");
        javaMailSender.send(message);
    }

}
