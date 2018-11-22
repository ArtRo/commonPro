package com.sangrade.base.api.service;

import com.sangrade.base.api.po.UserPo;
import com.sangrade.base.util.error.AppRuntimeException;

import java.util.Map;

public interface LoginService {

    String getToken(UserPo userPo);

    Long quickLoginOrRegByThird(UserPo userPo, Map<String, Object> result) throws AppRuntimeException;

    Boolean bind(UserPo userPo);
}
