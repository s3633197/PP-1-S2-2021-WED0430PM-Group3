package com.matchmaking.backend.config;


import com.matchmaking.backend.security.filter.TokenAuthenticationEntryPoint;
import com.matchmaking.backend.security.filter.TokenAuthenticationFilter;
import com.matchmaking.backend.security.handler.LoginFailureHandler;
import com.matchmaking.backend.security.handler.LoginSuccessHandler;
import com.matchmaking.backend.security.handler.TokenAccessDeniedHandler;
import com.matchmaking.backend.security.handler.TokenLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] URL_WHITELISTS = {
            "/login",
            "/logout",
            "/api/account/register",
            "/api/company/all",
            "/api/post/all",
            "/api/company/select/{companyId}",
            "/api/post/select/{postId}"
    };

    @Autowired
    LoginFailureHandler loginFailureHandler;


    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;

    @Autowired
    TokenAccessDeniedHandler accessDeniedHandler;

    @Autowired
    TokenLogoutSuccessHandler tokenLogoutSuccessHandler;


    @Bean
    TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception {
        TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter(authenticationManager());
        return  tokenAuthenticationFilter;
    }


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

        // manage logout process
        .and()
        .logout()
        .logoutSuccessHandler(tokenLogoutSuccessHandler)

        // manage authentication of URL
        .and()
        .authorizeRequests()
        .antMatchers(URL_WHITELISTS).permitAll()
        .anyRequest().authenticated()


        // disable session
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // handle authentication exception
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(tokenAuthenticationEntryPoint)
        .accessDeniedHandler(accessDeniedHandler)

        // manage token status
        .and()
        .addFilter(tokenAuthenticationFilter())
        ;
    }
}
