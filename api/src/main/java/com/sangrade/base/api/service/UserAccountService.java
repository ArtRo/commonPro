package com.sangrade.base.api.service;

import com.sangrade.base.api.model.UserAccount;

import java.util.List;

public interface UserAccountService {

    List<UserAccount> selectByEntity(UserAccount record);
    
    UserAccount selectById(Long id);

    int insert(UserAccount record);
    
    int deleteById(Long id);
    
    int updateByEntity(UserAccount record);
    
}