package com.sangrade.base.api.third;

import com.sangrade.base.api.po.UserPo;
import com.sangrade.base.util.Constants.LoginType;
import com.sangrade.base.util.error.AppRuntimeException;

public interface ThirdInfoService {
    String GRANT_TYPE = "authorization_code", WEIBO_SCOPE = "all", WEIXIN_SCOPE = "snsapi_userinfo", QQ_SCOPE = "get_user_info";
    /**
     * 解绑三方
     *
     * @param profileId
     * @param thirdPatryType 三方类型
     * @return
     */
    Object thirdUntie(Long profileId, LoginType thirdPatryType) throws AppRuntimeException;

    Object thirdBind(UserPo userPo) throws AppRuntimeException;

    Object getThirdPartyUserInfo(String loginType, Integer type, String code, String accessToken, String openid, UserPo userPo) throws AppRuntimeException;
}
