package com.sangrade.base.api.service;

import com.sangrade.base.api.model.UserLogin;

import java.util.List;

public interface UserLoginService {

    List<UserLogin> selectByEntity(UserLogin record);
    
    UserLogin selectById(Long id);

    int insert(UserLogin record);
    
    int deleteById(Long id);
    
    int updateByEntity(UserLogin record);
    
}