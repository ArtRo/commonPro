package com.sangrade.base.api.third;

import com.sangrade.base.api.po.UserPo;

public interface UserActionService {
    //注册
    Object thirdInfo(String accessToken, String appSecret, String openid, String appId, UserPo userPo);
}
