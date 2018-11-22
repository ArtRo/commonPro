package com.sangrade.base.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sangrade.base.api.model.UserLogin;
import com.sangrade.base.api.service.UserLoginService;
import com.sangrade.base.provider.dao.UserLoginDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginDao userLoginDao;
    
    @Override
    public List<UserLogin> selectByEntity(UserLogin record) {
        List<UserLogin> result = userLoginDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public UserLogin selectById(Long id) {
        UserLogin result = userLoginDao.selectById(id);
        return result;
    }

    @Override
    public int insert(UserLogin record) {
         return userLoginDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(UserLogin record) {
        return userLoginDao.updateByEntity(record);
    }    

    @Override
    public int deleteById(Long id) {
        return userLoginDao.deleteById(id);
    }
    
}