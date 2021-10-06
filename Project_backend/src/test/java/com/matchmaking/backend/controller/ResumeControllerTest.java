package com.matchmaking.backend.controller;

import cn.hutool.json.JSONUtil;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.entity.Resume;
import com.matchmaking.backend.utils.ConvertorUtils;
import com.matchmaking.backend.utils.JwtUtils;
import com.matchmaking.backend.utils.RedistUtils;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class ResumeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RedistUtils redistUtils;

    private Resume resume;

    private String token;

    private String CVToken;

    private static final String API = "/api/seeker";

    private static final String EMAIL = "seeker@test.com";

    private static final String CVEMAIL = "ken6@test.com";

    @BeforeEach
    void setUp(){
        System.out.println("---------Start testing company API---------");


        resume = new Resume();
        resume.setDateOfBirth(ConvertorUtils.stringToLocalDate("1998-12-01"));
        resume.setEducationalBackground("Diploma");
        resume.setExpectedSalary(20);
        resume.setFirstName("Lin");
        resume.setLastName("Lin");
        resume.setGender("male");
        resume.setJobType("Full-time");
        resume.setLocation("RMIT");
        resume.setMajor("IT");
        resume.setPhone(12313213);
        resume.setSkill("Test");
        resume.setStatement("Good");


        // mock account
        token = jwtUtils.generateToken(EMAIL);
        redistUtils.set(token,EMAIL,60*60);

        // Has CV account
        CVToken = jwtUtils.generateToken(CVEMAIL);
        redistUtils.set(CVToken,CVEMAIL,60*60);

    }


    @Test
    void createResume() throws Exception {
        resume.setAccountId(21);
        mockMvc.perform(MockMvcRequestBuilders
            .post(API+"/create")
            .header("Authentication",token)
            .content(JSONUtil.parse(resume).toStringPretty())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(print());
    }

    @Test
    void getResume() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get(API+"/resume")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authentication",CVToken)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }


    // No CV test
    @Test
    void getResumeFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get(API+"/resume")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authentication",token)
        )
        .andExpect(MockMvcResultMatchers.content().json(JSONUtil.formatJsonStr("{\"message\":\"Please create your CV first\",\"data\":null,\"statusCode\":400}")))
        .andDo(print());
    }

    @Test
    void updateResume() throws Exception {
        resume.setAccountId(1);
        mockMvc.perform(MockMvcRequestBuilders
                .put(API+"/update")
                .content(JSONUtil.parse(resume).toStringPretty())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authentication",CVToken)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}