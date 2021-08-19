package com.matchmaking.backend.controller;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @PreAuthorize("hasRole('Company')" )
    @GetMapping
    public Result test(){
        return Result.success("","Ok");
    }
}
