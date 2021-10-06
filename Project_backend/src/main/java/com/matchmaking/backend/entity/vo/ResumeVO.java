package com.matchmaking.backend.entity.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResumeVO {
    private int seekerId;

    private String firstName;

    private String lastName;

    private String gender;

    private int phone;

    private LocalDate dateOfBirth;

    private String schoolName;

    private String major;

    private String educationalBackground;

    private String skill;

    private String statement;

    private String location;

    private String wantedIndustry;

    private String jobType;

    private long expectedSalary;

    public ResumeVO(int seekerId, String firstName, String lastName, String gender, int phone, LocalDate dateOfBirth, String schoolName, String major, String educationalBackground, String skill,
                    String statement, String location, String wantedIndustry, String jobType, long expectedSalary) {
        this.seekerId = seekerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.schoolName = schoolName;
        this.major = major;
        this.educationalBackground = educationalBackground;
        this.skill = skill;
        this.statement = statement;
        this.location = location;
        this.wantedIndustry = wantedIndustry;
        this.jobType = jobType;
        this.expectedSalary = expectedSalary;
    }
}
