package com.matchmaking.backend.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String email;

    private String password;

    private Long userId;

    private String nickname;

    private int status;

    private String avatar;

    private LocalDateTime createTime;

    private LocalDateTime lastLoginTime;

}
