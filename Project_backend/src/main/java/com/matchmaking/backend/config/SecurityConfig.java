package com.matchmaking.backend.config;


import com.matchmaking.backend.security.handler.LoginFailureHandler;
import com.matchmaking.backend.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] URL_WHITELISTS = {
            "/login",
            "/logout",
            "/api/user/*"
    };

    @Autowired
    LoginFailureHandler loginFailureHandler;


    @Autowired
    LoginSuccessHandler loginSuccessHandler;



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()

        // enable form login
        .formLogin()
        .successHandler(loginSuccessHandler)
        .failureHandler(loginFailureHandler)

        .and()
        .authorizeRequests()
        .antMatchers(URL_WHITELISTS).permitAll()
        .anyRequest().authenticated()


        // disable session
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }
}
