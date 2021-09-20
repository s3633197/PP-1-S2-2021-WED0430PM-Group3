package com.matchmaking.backend.controller;

import cn.hutool.json.JSONUtil;
import com.matchmaking.backend.entity.Account;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.OutputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String APIHOST = "/api/account";

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("---------Start testing account API---------");
        sendEmail();
        register();
        login();
        System.out.println("=========Test completed=========");
    }

    @Test
    void sendEmail() throws Exception {
        String testEmail = "unit@test.com";
        mockMvc.perform(MockMvcRequestBuilders
                .get(APIHOST+"/verify/email")
                .content(testEmail)
                .accept(MediaType.ALL)
                .contentType(MediaType.ALL)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void register() throws Exception {

        // mock account
        Account account = new Account();
        account.setEmail("unit@test.com");
        account.setPassword("12345");
        account.setRoleId(1);
        account.setVerifyCode("12345");
        mockMvc.perform(MockMvcRequestBuilders
                .post(APIHOST+"/register")
                .content(JSONUtil.parse(account).toStringPretty())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print());
    }

    @Test
    void login(){

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/login")
                    .param("username","linlin@test.com") // test account
                    .param("password","123456")
                    .accept(MediaType.MULTIPART_FORM_DATA)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginFail(){

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/login")
                    .param("username","linlin@test.com") // test account
                    .param("password","12345")
                    .accept(MediaType.MULTIPART_FORM_DATA)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
            )
                    .andExpect(MockMvcResultMatchers.status().is(500))
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
