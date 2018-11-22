package com.sangrade.base.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sangrade.base.api.model.ThirdProfile;
import com.sangrade.base.api.service.ThirdProfileService;
import com.sangrade.base.provider.dao.ThirdProfileDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ThirdProfileServiceImpl implements ThirdProfileService {

    @Autowired
    private ThirdProfileDao thirdProfileDao;
    
    @Override
    public List<ThirdProfile> selectByEntity(ThirdProfile record) {
        List<ThirdProfile> result = thirdProfileDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public ThirdProfile selectById(Long id) {
        ThirdProfile result = thirdProfileDao.selectById(id);
        return result;
    }

    @Override
    public int insert(ThirdProfile record) {
         return thirdProfileDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(ThirdProfile record) {
        return thirdProfileDao.updateByEntity(record);
    }    

    @Override
    public int deleteById(Long id) {
        return thirdProfileDao.deleteById(id);
    }
    
}