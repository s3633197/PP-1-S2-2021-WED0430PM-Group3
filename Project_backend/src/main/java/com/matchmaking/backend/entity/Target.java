package com.matchmaking.backend.entity;

import com.matchmaking.backend.common.lang.Degree;
import com.matchmaking.backend.common.lang.JobType;
import lombok.Data;


@Data
public class Target {

    private JobType jobType;

    private Degree degree;

    private int expectedSalary;

}
