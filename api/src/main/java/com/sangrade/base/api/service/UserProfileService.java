package com.sangrade.base.api.service;

import com.sangrade.base.api.model.UserProfile;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> selectByEntity(UserProfile record);
    
    UserProfile selectById(Long id);

    int insert(UserProfile record);
    
    int deleteById(Long id);
    
    int updateByEntity(UserProfile record);
    
}