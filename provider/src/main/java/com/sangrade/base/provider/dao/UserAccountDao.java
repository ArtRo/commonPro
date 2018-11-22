package com.sangrade.base.provider.dao;

import com.sangrade.base.api.model.UserAccount;

import java.util.List;

public interface UserAccountDao {

    List<UserAccount> selectByEntity(UserAccount record);
    
    UserAccount selectById(Long id);

    int insert(UserAccount record);
  
    int deleteById(Long id);
    
    int updateByEntity(UserAccount record);
    
}