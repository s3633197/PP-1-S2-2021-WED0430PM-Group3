package com.matchmaking.backend.entity;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Company {
    private int companyId;

    private String companyName;

    private LocalDate startUpDate;

    private String owner;

    private int accountId;
}
