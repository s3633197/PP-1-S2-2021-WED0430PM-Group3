package com.matchmaking.backend.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String username;

    private String password;

    private Long userId;

    private LocalDateTime createdTime;

    private LocalDateTime lastLoginTime;

}
