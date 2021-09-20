package com.matchmaking.backend.security;

import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.service.AccountService;
import com.matchmaking.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountService accountService;


    @Autowired
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account user = accountService.findAccountByEmail(email);

        if(user != null){
            System.out.println(user);
            return new AccountDetails((long) user.getAccountId(),user.getPassword(),user.getEmail(),getAuthorities(user.getRoleId()));
        }else{
            throw new UsernameNotFoundException("Email does not exist");
        }
    }


    public List<GrantedAuthority> getAuthorities(int roleId){
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = roleService.getAccountAuthority(roleId);
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

}
