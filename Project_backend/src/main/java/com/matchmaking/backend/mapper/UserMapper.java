package com.matchmaking.backend.mapper;


import com.matchmaking.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {

    User findUserByEmail(String email);

    void createUser(User user);

    void updateLoginTime(String email, LocalDateTime loginTime);

}
