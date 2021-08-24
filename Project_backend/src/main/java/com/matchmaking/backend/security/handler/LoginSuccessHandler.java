package com.matchmaking.backend.security.handler;

import cn.hutool.json.JSONUtil;
import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.entity.vo.AccountVO;
import com.matchmaking.backend.service.AccountService;
import com.matchmaking.backend.utils.JwtUtils;
import com.matchmaking.backend.utils.RedistUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    AccountService accountService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RedistUtils redistUtils;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
    Account account = accountService.findAccountByEmail(authentication.getName());

    // create token
    String token = jwtUtils.generateToken(account.getEmail());

    // add token to header
    response.setHeader(jwtUtils.getHeader(),token);
    response.setContentType("application/json;charset=UTF-8");


        // store token in redis for 1 hours
    redistUtils.set(token,account.getEmail(),60*60);

    ServletOutputStream outputStream = response.getOutputStream();
    accountService.updateLoginTime(account.getEmail());

    AccountVO accountVO = dozerBeanMapper.map(account,AccountVO.class);
    Result result = Result.success(accountVO,"Successfully login");
    outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

    outputStream.flush();
    outputStream.close();


    }
}
