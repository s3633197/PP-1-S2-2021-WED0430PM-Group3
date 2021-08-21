package com.matchmaking.backend.entity.dto;


import lombok.Data;

@Data
public class PasswordDTO {

    private String email;
    private String oldPassword;
    private String newPassword;
}
