package com.matchmaking.backend.controller;

import com.matchmaking.backend.common.algorithm.KMPAlgorithm;
import com.matchmaking.backend.common.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    KMPAlgorithm kmpAlgorithm;
    @GetMapping("/kmp")
    public Result testKmp(){
        String target = "staffingHuman";
        String matchItem = "Automotive, Staffing and Recruiting, and Human Resources";

        int next[] = kmpAlgorithm.kmpNext(matchItem);
        int index = kmpAlgorithm.kmpSearch(target,matchItem,next);

        return Result.success(index);
    }
}
