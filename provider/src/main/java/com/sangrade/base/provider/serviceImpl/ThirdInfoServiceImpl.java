package com.sangrade.base.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sangrade.base.api.model.ThirdInfo;
import com.sangrade.base.api.service.ThirdInfoService;
import com.sangrade.base.provider.dao.ThirdInfoDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ThirdInfoServiceImpl implements ThirdInfoService {

    @Autowired
    private ThirdInfoDao thirdInfoDao;
    
    @Override
    public List<ThirdInfo> selectByEntity(ThirdInfo record) {
        List<ThirdInfo> result = thirdInfoDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public ThirdInfo selectById(Long id) {
        ThirdInfo result = thirdInfoDao.selectById(id);
        return result;
    }

    @Override
    public int insert(ThirdInfo record) {
         return thirdInfoDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(ThirdInfo record) {
        return thirdInfoDao.updateByEntity(record);
    }    

    @Override
    public int deleteById(Long id) {
        return thirdInfoDao.deleteById(id);
    }
    
}