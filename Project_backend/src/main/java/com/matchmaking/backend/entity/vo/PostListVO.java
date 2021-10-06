package com.matchmaking.backend.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PostListVO {

    private int postId;

    private String title;

    private long minSalary;

    private long maxSalary;

    private String educationalBackground;

    private String location;

    private String industry;

    private String jobType;

    @JsonFormat(pattern = "yyyy")
    private Date startUpYear;

    private String companyName;

    private int companyId;


    public PostListVO(int postId, String title, long minSalary, long maxSalary, String educationalBackground, String location, String industry,
                      String jobType, Date startUpYear, String companyName,int companyId) {
        this.postId = postId;
        this.title = title;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.educationalBackground = educationalBackground;
        this.location = location;
        this.industry = industry;
        this.jobType = jobType;
        this.companyId = companyId;
        this.startUpYear = startUpYear;
        this.companyName = companyName;
    }
}
