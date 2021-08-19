package com.matchmaking.backend.security.filter;

import cn.hutool.core.util.StrUtil;
import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.security.UserDetailsServiceImpl;
import com.matchmaking.backend.service.AccountService;
import com.matchmaking.backend.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AccountService accountService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
        // get token from header
        String jwt = request.getHeader(jwtUtils.getHeader());


        if(StrUtil.isBlankOrUndefined(jwt)){
            chain.doFilter(request,response);
            return;
        }

        // get authentication of current user
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            chain.doFilter(request,response);
            return;
        }

        //get claim
        Claims claims = jwtUtils.getClaimByToken(jwt);

        String email = claims.getSubject();

        Account account = accountService.findAccountByEmail(email);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,null,userDetailsService.getAuthorities(account.getRoleId()));

        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request,response);

    }
}
