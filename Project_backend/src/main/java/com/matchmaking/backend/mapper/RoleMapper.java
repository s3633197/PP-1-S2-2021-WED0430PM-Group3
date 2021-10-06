package com.matchmaking.backend.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    String getRole(int roleId);

}
