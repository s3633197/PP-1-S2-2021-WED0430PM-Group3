package com.matchmaking.backend.service;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.User;
import com.matchmaking.backend.mapper.UserMapper;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Result createUser(User user){
       User existUser = this.findUserByEmail(user.getEmail());
       // Create user if email does no exist
       if(existUser == null){
            user.setCreateTime(LocalDateTime.now());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userMapper.createUser(user);
            return Result.create("","Account Created");
       }
       return Result.failed("","Email was taken");
    }

    public User findUserByEmail(String email){
        // 需要抛出找不到用户的异常
      return userMapper.findUserByEmail(email);
    }


    public Result updateLoginTime(String email){
        userMapper.updateLoginTime(email, LocalDateTime.now());
        return Result.success("","Successfully Login");
    }


    public Result changePassword(String email,String oldPassword,String newPassword){
        // get current user
        User user = this.findUserByEmail(email);
        if(user == null) {
            return Result.failed("","Email does not exists");
        }
        // validate old password
        Boolean correct = bCryptPasswordEncoder.matches(oldPassword,user.getPassword());
        if(!correct){
            return Result.failed("","Incorrect password");
        }
        // 密码更新后需要删掉对应的token缓存

        userMapper.updatePassword(email,oldPassword,newPassword);
        return Result.success("Password changed");
    }



    public boolean isNicknameRepeat(String nickname){
        userMapper.checkNickname(nickname);


        return true;
    }


}
