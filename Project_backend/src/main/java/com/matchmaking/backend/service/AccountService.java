package com.matchmaking.backend.service;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.mapper.AccountMapper;
import com.matchmaking.backend.utils.JwtUtils;
import com.matchmaking.backend.utils.RedistUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

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

    public Result createAccount(Account account){
       Account existUser = this.findAccountByEmail(account.getEmail());
       // Create user if email does no exist
       if(existUser == null){
            account.setCreateTime(LocalDateTime.now());
            account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
            accountMapper.createAccount(account);
            return Result.create("","Account Created");
       }
       return Result.failed("Email was taken");
    }

    public Account findAccountByEmail(String email){
        // 需要抛出找不到用户的异常
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
        System.out.println(currentAccount().getRoleId());
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



}
