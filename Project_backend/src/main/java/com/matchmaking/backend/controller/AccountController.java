package com.matchmaking.backend.controller;


import com.google.code.kaptcha.Producer;
import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.entity.dto.PasswordDTO;
import com.matchmaking.backend.service.AccountService;
import com.matchmaking.backend.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public Result signUp(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @PutMapping("/changePassword")
    public Result changePassword(@RequestBody PasswordDTO passwordDTO){
        return accountService.changePassword(passwordDTO.getEmail(),passwordDTO.getOldPassword(),passwordDTO.getNewPassword());
    }

    @GetMapping("/auth/company")
    public Result checkAuthCompany(){
        return accountService.checkAuthCompany();
    }

    @GetMapping("/auth/seeker")
    public Result checkAuthSeeker(){
        return accountService.checkAuthSeeker();
    }

    @GetMapping("/verify/email")
    public Result getEmailCaptcha(@RequestBody String email){
        return accountService.sendEmail(email);
    }

}
