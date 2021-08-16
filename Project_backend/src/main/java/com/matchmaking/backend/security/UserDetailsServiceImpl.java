package com.matchmaking.backend.security;

import com.matchmaking.backend.entity.User;
import com.matchmaking.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);

        if(user != null){
            return new AccountDetails(user.getUserId(),user.getPassword(),user.getEmail(),null);
        }else{
            throw new UsernameNotFoundException("Email does not exist");
        }
    }


    public String getAuthorities(){
        return null;
    }

}
