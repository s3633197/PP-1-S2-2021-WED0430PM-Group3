package com.matchmaking.backend.service;

import com.matchmaking.backend.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public String getAccountAuthority(int roleId){
        return "ROLE_"+roleMapper.getRole(roleId);
    }

}
