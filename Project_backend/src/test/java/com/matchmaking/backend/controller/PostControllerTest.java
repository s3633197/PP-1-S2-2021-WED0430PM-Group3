package com.matchmaking.backend.controller;

import cn.hutool.json.JSONUtil;
import com.matchmaking.backend.entity.Company;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.utils.JwtUtils;
import com.matchmaking.backend.utils.RedistUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RedistUtils redistUtils;

    private Company company;

    private Post post;

    private String token;

    private String validToken;

    private static final String API = "/api/post";



    @BeforeEach
    void setUp(){
        System.out.println("---------Start testing post API---------");

        // mock data
        post = new Post();
        post.setIndustry("IT");
        post.setAddress("RMIT");
        post.setDescription("Testing mock data API");
        post.setEducationalBackground("Diploma");
        post.setEmploymentType("Full-time");
        post.setMaxSalary(19);
        post.setMinSalary(15);
        post.setTitle("Requirement Title");
        post.setPosition("Senior");

        // company info token
        validToken = jwtUtils.generateToken("linlin@test.com");
        redistUtils.set(validToken,"linlin@test.com",60*60);
    }


    @Test
    void createPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post(API+"/create")
            .content(JSONUtil.parse(post).toStringPretty())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .header("Authentication",validToken)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(print());
    }

    @Test
    void getAllPosts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get(API+"/all")
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(print());
    }

    @Test
    void updatePost() throws Exception {
        post.setCompanyId(2);
        mockMvc.perform(MockMvcRequestBuilders
                .post(API+"/update/4")
                .content(JSONUtil.parse(post).toStringPretty())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authentication",validToken)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }


}