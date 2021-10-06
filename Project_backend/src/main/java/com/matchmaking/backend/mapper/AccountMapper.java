package com.matchmaking.backend.mapper;


import com.matchmaking.backend.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface AccountMapper {

    Account findAccountByEmail(String email);

    void createAccount(Account account);

    void updateLoginTime(String email, LocalDateTime loginTime);

    void updatePassword(String email,String oldPassword,String newPassword);



}
