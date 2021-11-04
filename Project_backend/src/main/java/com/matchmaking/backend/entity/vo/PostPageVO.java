package com.matchmaking.backend.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.dozer.Mapping;

import java.util.Date;

@Data
public class PostPageVO {

    private String position;

    private String title;

    @Mapping("address")
    private String location;

    private String educationalBackground;

    private long maxSalary;

    private String description;

    private String companyName;

    private String owner;

    private String employmentType;

    private String industry;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startUpDate;

}
