package com.sangrade.base.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sangrade.base.api.model.UserLogin;
import com.sangrade.base.api.po.UserPo;
import com.sangrade.base.api.service.LoginService;
import com.sangrade.base.provider.dao.UserLoginDao;
import com.sangrade.base.util.Constants.AccountType;
import com.sangrade.base.util.Constants.InfoCode;
import com.sangrade.base.util.error.AppRuntimeException;
import com.sangrade.base.util.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserLoginDao userLoginDao;

    @Override
    public String getToken(UserPo userPo)throws  AppRuntimeException{
        UserLogin userLogin = new UserLogin();
        Long accountId = userPo.getAccountId();
        if(null != accountId && accountId > 0){
            userLogin.setAccountId(accountId);
            String token="";
            List<UserLogin> userLogins = userLoginDao.selectByEntity(userLogin);
            if(null != userLogins && userLogins.size() >0){
                UserLogin userLogin1 = userLogins.get(0);
                if(StringUtils.isEmpty(userLogin1.getToken())){
                    UUID uuid = UUID.randomUUID();
                    token = ""+Long.toHexString(System.currentTimeMillis())+ "-"+ Long.toHexString(uuid.getMostSignificantBits())+ Long.toHexString(uuid.getLeastSignificantBits());
                }
                userLogin1.setExpireTime(System.currentTimeMillis()+1000*60*60*24*365);
                userLoginDao.updateByEntity(userLogin1);
                return userLogin1.getToken();
            }else {
                userLogin.setExpireTime(System.currentTimeMillis()+1000*60*60*24*365);
                UUID uuid = UUID.randomUUID();
                token = ""+Long.toHexString(System.currentTimeMillis())+ "-"+ Long.toHexString(uuid.getMostSignificantBits())+ Long.toHexString(uuid.getLeastSignificantBits());
                int insert = userLoginDao.insert(userLogin);
                return token;
            }
        }else throw new AppRuntimeException(InfoCode.THE_BOUND_USER_DOES_NOT_EXIST);
    }

    @Override
    public Long quickLoginOrRegByThird(UserPo userPo, Map<String, Object> result) throws AppRuntimeException {
        return null;
    }

    @Override
    public Boolean bind(UserPo userPo) throws AppRuntimeException {
        AccountType accountType = userPo.getAccountType();
        Long accountId = userPo.getAccountId();

        return null;
    }

}
