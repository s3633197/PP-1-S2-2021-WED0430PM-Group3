package com.matchmaking.backend.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String email;

    private String password;

    private Long userId;

    private int roleId;

    private LocalDateTime createTime;

    private LocalDateTime lastLoginTime;

}
