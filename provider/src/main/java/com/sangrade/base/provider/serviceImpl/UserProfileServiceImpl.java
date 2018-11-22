package com.sangrade.base.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sangrade.base.api.model.UserProfile;
import com.sangrade.base.api.service.UserProfileService;
import com.sangrade.base.provider.dao.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;
    
    @Override
    public List<UserProfile> selectByEntity(UserProfile record) {
        List<UserProfile> result = userProfileDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public UserProfile selectById(Long id) {
        UserProfile result = userProfileDao.selectById(id);
        return result;
    }

    @Override
    public int insert(UserProfile record) {
         return userProfileDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(UserProfile record) {
        return userProfileDao.updateByEntity(record);
    }    

    @Override
    public int deleteById(Long id) {
        return userProfileDao.deleteById(id);
    }
    
}