package com.matchmaking.backend.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AccountVO {
    private int userId;

    private String email;

    private int accountId;

    private int roleId;


}
