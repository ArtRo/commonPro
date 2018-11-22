package com.sangrade.base.provider.dao;

import com.sangrade.base.api.model.ThirdProfile;

import java.util.List;

public interface ThirdProfileDao {

    List<ThirdProfile> selectByEntity(ThirdProfile record);
    
    ThirdProfile selectById(Long id);

    int insert(ThirdProfile record);
  
    int deleteById(Long id);
    
    int updateByEntity(ThirdProfile record);
    
}