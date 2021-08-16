package com.matchmaking.backend.controller;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.DTO.PasswordDTO;
import com.matchmaking.backend.entity.User;
import com.matchmaking.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result signUp(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/changePassword")
    public Result changePassword(@RequestBody PasswordDTO passwordDTO){
        return userService.changePassword(passwordDTO.getEmail(),passwordDTO.getOldPassword(),passwordDTO.getNewPassword());
    }



}
