package com.matchmaking.backend.mapper;


import com.matchmaking.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findUserByEmail(String email);

    void createUser(User user);

}
