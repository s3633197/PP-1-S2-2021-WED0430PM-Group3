package com.matchmaking.backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class EmailUtil {

    @Autowired
    JavaMailSenderImpl mailSender;


    public void sendVerifyEmail(String email,String code) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setSubject("Verify your email address");
        String content ="<html lang=\"en\" style=\"width: 100%;\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div style=\"display: flex; flex-direction:column; justify-content:center; align-items:center; background-color:white; border-radius:10px; padding:0 10px;\">\n" +
                "            <h2 style=\"font-size:30px; margin: 20px; font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Welcome to Elite Net</h2>\n" +
                "            <div>\n" +
                "                <span style=\"font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;\"><b>Register verification code</b></span>\n" +
                "            </div>\n" +
                "            <div style=\"border-radius: 10px; background-color:#f9f9f9; padding:5px 10px ; width:300px; margin:20px;\">\n" +
                "                <p style=\"text-align: center; font-size:3rem; color:#357ae8;font-weight:bolder; font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;\">"+code+"</p>\n" +
                "            </div>\n" +
                "            <div>Secure your Elite Net account by veifying your email address</div>\n" +
                "            <p>The verification code will <span style=\"color: red;\">expire</span> after<b > 15 minutes</b></p>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";
        String plainText = "Verification code : " + code;
        helper.setText(content,true);
        helper.setFrom(new InternetAddress("kenlbd61@126.com","Elite Net","UTF-8"));
        helper.setTo(email);
        mailSender.send(message);

    }




}
