package com.matchmaking.backend.controller;


import com.matchmaking.backend.common.lang.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {


    @GetMapping("/testCase")
    public String test() {
    return null;
    }
}
