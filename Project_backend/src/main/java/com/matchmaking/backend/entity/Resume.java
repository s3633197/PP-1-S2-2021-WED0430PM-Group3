package com.matchmaking.backend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Resume {

    private int seekerId;

    private String firstName;

    private String lastName;

    private String gender;

    private int phone;

    private LocalDateTime DOB;

    private String schoolName;

    private String major;

    private String educationalBackground;

    private String skill;

    private String statement;

    private String location;

    private String wantedIndustry;

    private String jobType;

    private String expectedCompany;

    private long expectedSalary;

    private int accountId;

}
