package com.sangrade.base.provider.dao;

import com.sangrade.base.api.model.UserLogin;

import java.util.List;

public interface UserLoginDao {

    List<UserLogin> selectByEntity(UserLogin record);
    
    UserLogin selectById(Long id);

    int insert(UserLogin record);
  
    int deleteById(Long id);
    
    int updateByEntity(UserLogin record);

    int deleteByAccountIdAndLoginName(Long accountId,String openId);
    
}