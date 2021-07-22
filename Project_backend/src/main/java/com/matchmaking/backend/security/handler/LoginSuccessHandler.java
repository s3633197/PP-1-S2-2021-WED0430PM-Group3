package com.matchmaking.backend.security.handler;

import cn.hutool.json.JSONUtil;
import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.User;
import com.matchmaking.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
    User user = userService.findUserByEmail(authentication.getName());


    ServletOutputStream outputStream = response.getOutputStream();
    Result result =  userService.updateLoginTime(user.getEmail());

    outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

    outputStream.flush();
    outputStream.close();


    }
}
