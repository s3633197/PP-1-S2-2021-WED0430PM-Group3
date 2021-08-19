package com.matchmaking.backend.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {
    private String email;

    private String password;

    private int accountId;

    private int roleId;

    private LocalDateTime createTime;

    private LocalDateTime lastLoginTime;

}
