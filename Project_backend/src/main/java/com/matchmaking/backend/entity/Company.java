package com.matchmaking.backend.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Data
public class Company {

    private int companyId;

    private String companyName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(
            pattern = "dd-MM-yyyy"
    )
    private Date startUpDate;

    private String owner;

    private String industry;

    private int accountId;
}
