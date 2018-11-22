package com.sangrade.base.provider.third;

import com.alibaba.dubbo.config.annotation.Service;
import com.beust.jcommander.internal.Maps;
import com.sangrade.base.api.model.ThirdInfo;
import com.sangrade.base.api.model.ThirdProfile;
import com.sangrade.base.api.model.UserAccount;
import com.sangrade.base.api.model.UserProfile;
import com.sangrade.base.api.po.UserPo;
import com.sangrade.base.api.service.LoginService;
import com.sangrade.base.api.service.UserProfileService;
import com.sangrade.base.api.third.ThirdInfoService;
import com.sangrade.base.provider.dao.*;
import com.sangrade.base.provider.third.QQ.QqServiceApi;
import com.sangrade.base.provider.third.WB.WeiboServiceApi;
import com.sangrade.base.provider.third.weixin.wxLogin.WeiXinServiceApi;
import com.sangrade.base.util.Constants.Constant;
import com.sangrade.base.util.Constants.InfoCode;
import com.sangrade.base.util.Constants.LoginType;
import com.sangrade.base.util.error.AppRuntimeException;
import com.sangrade.base.util.string.MatchStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThirdPartyInfoServiceImpl implements ThirdInfoService {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserProfileDao userProfileDao;

    @Autowired
    ThirdInfoDao thirdInfoDao;

    @Autowired
    ThirdProfileDao thirdProfileDao;

    @Autowired
    UserAccountDao userAccountDao;

    @Autowired
    UserLoginDao userLoginDao;

    @Autowired
    LoginService loginService;

    Logger logger = LoggerFactory.getLogger(ThirdPartyInfoServiceImpl.class);

    @Override
    public Object getThirdPartyUserInfo(String loginType, Integer type, String code, String accessToken, String openid, UserPo userPo) throws AppRuntimeException {
        LoginType thirdPatryType = LoginType.valueOf(loginType);
        HashMap<String, Object> result = new HashMap<>();
        if (null != thirdPatryType) {
            ThirdProfile thirdPartyInfo = new ThirdProfile();
            thirdPartyInfo.setName(thirdPatryType.getDesc());
            thirdPartyInfo.setIsDel(Constant.TABLE_PARAM_NORMAL);
            List<ThirdProfile> thirdProfiles = thirdProfileDao.selectByEntity(thirdPartyInfo);
            if (null == thirdProfiles || thirdProfiles.size() <= 0) {
                throw new AppRuntimeException(InfoCode.NOT_SUPPORT_THIRD_PARTY_TYPES);
            } else {
                ThirdProfile thirdProfile = thirdProfiles.get(0);
                String collbackUrl = thirdProfile.getCallBackUrl();
                String domainName = thirdProfile.getDomainName();
                String thirdKey = thirdProfile.getAccountKey();
                String thirdSecret = thirdProfile.getAccountSecret();
                if (thirdPatryType.getType().equals(LoginType.QQ_LOGIN.getType())) {
                    //登陆
                    if (type == 0 && thirdPartyUpload(openid, LoginType.QQ_LOGIN.getType(), result, userPo) && result.size() > 0) {
                        return result;
                    }
                    //用户三方信息
                    QqServiceApi.getThirdUserServiceImpl().thirdInfo(accessToken, thirdSecret, openid, thirdKey, userPo);
                }
                if (thirdPatryType.getType().equals(LoginType.WEIBO_LOGIN.getType())) {
                    if (type == 0 && thirdPartyUpload(openid, LoginType.WEIBO_LOGIN.getType(), result, userPo) && result.size() > 0) {
                        return result;
                    }
                    WeiboServiceApi.getThirdUserServiceImpl().thirdInfo(accessToken, thirdSecret, openid, thirdKey, userPo);
                }
                if (thirdPatryType.getType().equals(LoginType.WX_LOGIN.getType())) {
                    WeiXinServiceApi.AccessToken access_Token = WeiXinServiceApi.getAccessToken(code, thirdKey, thirdSecret);
                    //登录
                    if (type == 0 && thirdPartyUpload(access_Token.getOpenid(), LoginType.WX_LOGIN.getType(), result, userPo) && result.size() > 0) {
                        return result;
                    }
                    WeiXinServiceApi.getThirdUserServiceImpl().thirdInfo(access_Token.getAccess_token(), thirdSecret, access_Token.getOpenid(), thirdKey, userPo);
                }
                try {
                    if (type == 1) {
                        //绑定
                        return thirdBind(userPo);
                    }
                    Long profileId = loginService.quickLoginOrRegByThird(userPo, result);
                    UserProfile userProfile = userProfileDao.selectById(profileId);
                    userPo.setProfileId(profileId);
                    userPo.setAccountId(userProfile.getAccountId());
                    String token = loginService.getToken(userPo);
                    result.put("profile_id", profileId);
                    result.put("token", token);
                } catch (AppRuntimeException e) {
                    e.printStackTrace();
                    logger.error("三方绑定或登录注册失败", e);
                    throw new AppRuntimeException(InfoCode.SYSTEM_ERROR);
                }
                return result;
            }
        } else {
            throw new AppRuntimeException(InfoCode.NOT_SUPPORT_THIRD_PARTY_TYPES);
        }
    }

    @Override
    public Object thirdUntie(Long profileId, LoginType thirdPatryType) throws AppRuntimeException {
        UserProfile userProfile = userProfileDao.selectById(profileId);
        if (null != userProfile) {
            ThirdInfo thirdInfo = new ThirdInfo();
            thirdInfo.setIsDel(Constant.TABLE_PARAM_NORMAL);
            thirdInfo.setAccountId(userProfile.getAccountId());
            List<ThirdInfo> thirdInfos = thirdInfoDao.selectByEntity(thirdInfo);
            if (null == thirdInfos || thirdInfos.size() + (MatchStringUtil.isMobile(userProfile.getTel().toString()) ? 1 : 0) <= 1) {
                throw new AppRuntimeException(InfoCode.THE_LAST_ACCOUNT_CANNOT_BE_UNTIED);
            }
            ThirdInfo thirdUserInfo1 = thirdInfos.get(0);
            thirdUserInfo1.setIsDel(Constant.TABLE_PARAM_DELETE);
            thirdInfoDao.updateByEntity(thirdUserInfo1);
//            userLoginDao.deleteByAccountIdAndLoginName(userProfile.getAccountId(), thirdUserInfo1.getOpenId());
            return new Integer(1);
        } else {
            throw new AppRuntimeException(InfoCode.INADEQUATE_USER_INFORMATION);
        }
    }

    @Override
    public Object thirdBind(UserPo userPo) throws AppRuntimeException {
        Map<Object, Object> result = Maps.newHashMap();
        UserProfile userProfile = userProfileDao.selectById(userPo.getProfileId());
        if (null != userProfile && userProfile.getAccountId() > 0) {
            userPo.setAccountId(userProfile.getAccountId());
            boolean bind = false;
            try {
                bind = loginService.bind(userPo);
                if (bind) {
                    ThirdInfo thirdUserInfo = new ThirdInfo();
                    thirdUserInfo.setAccountId(userPo.getAccountId());
                    thirdUserInfo.setThirdType(userPo.getLoginType().getType());
                    List<ThirdInfo> thirdUserInfos = thirdInfoDao.selectByEntity(thirdUserInfo);
                    if (null != thirdUserInfos && thirdUserInfos.size() > 0) {
                        //存在 绑定
                        ThirdInfo thirdUserInfo1 = thirdUserInfos.get(0);
                        if(thirdUserInfo1.getIsDel().equals(Constant.TABLE_PARAM_DELETE)){
                            thirdUserInfo1.setIsDel(Constant.TABLE_PARAM_NORMAL);
                            thirdInfoDao.updateByEntity(thirdUserInfo1);
                        }
                        result.put("third_name", thirdUserInfo1.getNickName());
                    } else {
                        //不存在 绑定
                        thirdUserInfo.setNickName(userPo.getNickName());
                        thirdUserInfo.setIsDel(Constant.TABLE_PARAM_NORMAL);
                        thirdInfoDao.insert(thirdUserInfo);
                        result.put("third_name", thirdUserInfo.getNickName());
                    }
                    return result;
                } else {
                    throw new AppRuntimeException(InfoCode.BINDING_FAILED);
                }
            } catch (AppRuntimeException e) {
                e.printStackTrace();
                throw new AppRuntimeException(InfoCode.BINDING_FAILED);
            }
        } else {
            throw new AppRuntimeException(InfoCode.THE_BOUND_USER_DOES_NOT_EXIST);
        }
    }

    //登陆
    private boolean thirdPartyUpload(String openid, Integer thirdType, HashMap<String, Object> result, UserPo userPo) {
        ThirdInfo thirdUserInfo = new ThirdInfo();
        thirdUserInfo.setOpenId(openid);
        thirdUserInfo.setThirdType(thirdType);
        thirdUserInfo.setIsDel(Constant.TABLE_PARAM_NORMAL);
        List<ThirdInfo> thirdUserInfos = thirdInfoDao.selectByEntity(thirdUserInfo);
        boolean upload = false;
        if (null != thirdUserInfos && thirdUserInfos.size() > 0) {
            ThirdInfo thirdUserInfo1 = thirdUserInfos.get(0);
            UserAccount userAccount = userAccountDao.selectById(thirdUserInfo1.getAccountId());
            Long accountId = userAccount.getId();
            if (null != accountId && accountId > 0) {
                UserProfile userProfile = new UserProfile();
                userProfile.setAccountId(accountId);
                List<UserProfile> userProfiles = userProfileDao.selectByEntity(userProfile);
                if (null != userProfiles && userProfiles.size() > 0) {
                    Long id = userProfiles.get(0).getId();
                    userPo.setProfileId(id);
                    userPo.setAccountId(accountId);
                    try {
                        String token = loginService.getToken(userPo);
                        result.put("profile_id", id);
                        result.put("token", token);
                        result.put("is_new_user", -1);
                        upload = true;
                    } catch (AppRuntimeException e) {
                        logger.error("第三方快速登录失败", e);
                        throw new AppRuntimeException(InfoCode.SYSTEM_ERROR);
                    }
                }
            }
        }
        return upload;
    }

}
