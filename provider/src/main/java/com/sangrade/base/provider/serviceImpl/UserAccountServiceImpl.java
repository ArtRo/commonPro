package com.sangrade.base.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sangrade.base.api.model.UserAccount;
import com.sangrade.base.api.service.UserAccountService;
import com.sangrade.base.provider.dao.UserAccountDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;
    
    @Override
    public List<UserAccount> selectByEntity(UserAccount record) {
        List<UserAccount> result = userAccountDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public UserAccount selectById(Long id) {
        UserAccount result = userAccountDao.selectById(id);
        return result;
    }

    @Override
    public int insert(UserAccount record) {
         return userAccountDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(UserAccount record) {
        return userAccountDao.updateByEntity(record);
    }    

    @Override
    public int deleteById(Long id) {
        return userAccountDao.deleteById(id);
    }
    
}