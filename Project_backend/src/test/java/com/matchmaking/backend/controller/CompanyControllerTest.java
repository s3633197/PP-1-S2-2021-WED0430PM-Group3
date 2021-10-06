package com.matchmaking.backend.controller;


import cn.hutool.json.JSONUtil;
import com.matchmaking.backend.entity.Company;
import com.matchmaking.backend.utils.JwtUtils;
import com.matchmaking.backend.utils.RedistUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RedistUtils redistUtils;

    private Company company;

    private String token;

    private String validToken;

    private static final String API = "/api/company";

    @BeforeEach
    public void setUp(){
        System.out.println("---------Start testing company API---------");

        // mock company information
        company = new Company();
        company.setCompanyName("Unit Test Company");
        company.setIndustry("IT");
        company.setOwner("Ken");
        company.setStartUpDate(new Date());
        // company role token
        token = jwtUtils.generateToken("company@test.com");
        redistUtils.set(token,"company@test.com",60*60);

        // company info token
        validToken = jwtUtils.generateToken("linlin@test.com");
        redistUtils.set(validToken,"linlin@test.com",60*60);

        System.out.println("Mock data created");
    }

    @AfterEach
    void tearDown(){
        redistUtils.del(validToken);
        redistUtils.del(token);
    }

    @Test
    void createCompanyInfo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
            .post(API+"/create")
            .header("Authentication",token)
            .content(JSONUtil.parse(company).toStringPretty())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print());
    }

    @Test
    void getCompanyInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        .get(API+"/info")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authentication",validToken)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print());
    }

    @Test
    void updateCompanyInfo() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
            .put(API+"/update")
            .content(JSONUtil.parse(company).toStringPretty())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .header("Authentication",validToken)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print());
    }

}
