package com.sangrade.base.provider.dao;

import com.sangrade.base.api.model.ThirdInfo;

import java.util.List;

public interface ThirdInfoDao {

    List<ThirdInfo> selectByEntity(ThirdInfo record);
    
    ThirdInfo selectById(Long id);

    int insert(ThirdInfo record);
  
    int deleteById(Long id);
    
    int updateByEntity(ThirdInfo record);
    
}