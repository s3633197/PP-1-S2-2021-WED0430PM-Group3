package com.matchmaking.backend.service;


import com.google.code.kaptcha.Producer;
import com.matchmaking.backend.common.lang.Const;
import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.mapper.AccountMapper;
import com.matchmaking.backend.utils.EmailUtil;
import com.matchmaking.backend.utils.JwtUtils;
import com.matchmaking.backend.utils.RedistUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    RedistUtils redistUtils;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    Producer producer;


    public Result createAccount(Account account){
       Account existUser = this.findAccountByEmail(account.getEmail());
       // Create user if email does no exist
       if(existUser == null){

           if(account.getVerifyCode().isEmpty()){
               return Result.failed("Email is not verify");
           }
            String code = (String) redistUtils.hget(Const.VERIFYEMAIL,account.getEmail());
           if(account.getVerifyCode().equals(code)){
               account.setCreateTime(LocalDateTime.now());
               account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
               accountMapper.createAccount(account);
               redistUtils.del(Const.VERIFYEMAIL);
               return Result.create("","Account Created");
           }

           return Result.failed("Invalid verification code");

       }
       return Result.failed("Email was taken");
    }

    public Account findAccountByEmail(String email){
      return accountMapper.findAccountByEmail(email);
    }


    public void updateLoginTime(String email){
        accountMapper.updateLoginTime(email, LocalDateTime.now());
    }


    public Result changePassword(String email,String oldPassword,String newPassword){
        // get current user
        Account account = this.findAccountByEmail(email);
        if(account == null) {
            return Result.failed("Email does not exists");
        }
        // validate old password
        Boolean correct = bCryptPasswordEncoder.matches(oldPassword,account.getPassword());
        if(!correct){
            return Result.failed("Incorrect password");
        }
        // 密码更新后需要删掉对应的token缓存

        accountMapper.updatePassword(email,oldPassword,newPassword);
        return Result.success("Password changed");
    }

    public Account currentAccount(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account currentAccount = accountMapper.findAccountByEmail(email);
        return  currentAccount;
    }

    public Result checkAuthCompany(){
        if(currentAccount().getRoleId() != 2){
            return Result.notAuthorised("Not authorised");
        }
        return Result.success("","Authorised");
    }

    public Result checkAuthSeeker(){
        if(currentAccount().getRoleId() != 1){
            return Result.notAuthorised("Not authorised");
        }
        return Result.success("","Authorised");
    }


    public Result sendEmail(String email){
        Account user = accountMapper.findAccountByEmail(email);
        if(user!= null){
            return Result.failed("Email was taken");
        }
        // generate verify code
        String code = producer.createText();
        try {
            // send email
            emailUtil.sendVerifyEmail(email,code);
            // store code in redis for validation
            redistUtils.hset(Const.VERIFYEMAIL,email,code,900);
            return Result.success("","Verification sent,please check your email ");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return Result.failed("Internal error, please try again");
        }
    }



}
