package com.sangrade.base.api.service;

import com.sangrade.base.api.model.ThirdProfile;

import java.util.List;

public interface ThirdProfileService {

    List<ThirdProfile> selectByEntity(ThirdProfile record);
    
    ThirdProfile selectById(Long id);

    int insert(ThirdProfile record);
    
    int deleteById(Long id);
    
    int updateByEntity(ThirdProfile record);
    
}