package com.sangrade.base.api.service;

import com.sangrade.base.api.model.ThirdInfo;

import java.util.List;

public interface ThirdInfoService {

    List<ThirdInfo> selectByEntity(ThirdInfo record);
    
    ThirdInfo selectById(Long id);

    int insert(ThirdInfo record);
    
    int deleteById(Long id);
    
    int updateByEntity(ThirdInfo record);
    
}