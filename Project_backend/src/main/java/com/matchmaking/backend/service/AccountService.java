package com.matchmaking.backend.service;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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


    public Result updateLoginTime(String email){
        accountMapper.updateLoginTime(email, LocalDateTime.now());
        return Result.success("","Successfully Login");
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




}
