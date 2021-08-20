package com.matchmaking.backend.entity;

import lombok.Data;

@Data
public class Post {

    private int postId;

    private String title;

    private String description;

    private String industry;

    private String position;

    private String address;

    private String employmentType;

    private String educationalBackground;

    private long minSalary;

    private long maxSalary;

    private int companyId;
}
