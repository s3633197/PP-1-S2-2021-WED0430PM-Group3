package com.matchmaking.backend.security;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

@Data
public class AccountDetails implements UserDetails {

    private String password;
    private String email;
    private List<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean rememberMe;
    private Long userId;



    public AccountDetails(Long userId,String password, String email, List<GrantedAuthority> authorities){
        this(userId,password,email,authorities,true,true,true,true,false);
    }
    public AccountDetails(Long userId,String password, String email, List<GrantedAuthority> authorities,
                          boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,boolean rememberMe) {
        this.userId =userId;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.rememberMe = rememberMe;
    }


    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
