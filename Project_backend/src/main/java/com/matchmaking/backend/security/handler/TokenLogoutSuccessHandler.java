package com.matchmaking.backend.security.handler;

import cn.hutool.json.JSONUtil;
import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class TokenLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
        }


        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setHeader(jwtUtils.getHeader(),"");

        Result result = Result.success("","Successfully logout");

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
