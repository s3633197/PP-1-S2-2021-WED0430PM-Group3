package com.matchmaking.backend.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.dozer.Mapping;

import java.util.Date;

@Data
public class PostPageVO {

    private String position;

    @Mapping("address")
    private String location;

    private String educationalBackground;

    private long maxSalary;

    private String description;

    private String companyName;

    private String owner;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startUpDate;

}
