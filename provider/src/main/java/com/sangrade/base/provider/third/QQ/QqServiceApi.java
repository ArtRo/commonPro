package com.sangrade.base.provider.third.QQ;

import com.google.gson.Gson;
import com.sangrade.base.api.po.UserPo;
import com.sangrade.base.api.third.UserActionService;
import com.sangrade.base.util.Collection.CollectionBuilder;
import com.sangrade.base.util.Constants.AccountType;
import com.sangrade.base.util.Constants.InfoCode;
import com.sangrade.base.util.Constants.LoginType;
import com.sangrade.base.util.error.AppRuntimeException;
import com.sangrade.base.util.kit.HttpKit;
import com.sangrade.base.util.string.MatchStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

import static com.sangrade.base.api.third.ThirdInfoService.GRANT_TYPE;


public class QqServiceApi implements UserActionService {
    private final static Logger logger = LoggerFactory.getLogger(QqServiceApi.class);

    private final static String GET_AUTH_CODE = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
            "&client_id={client_id}&state={state}&scope={scope}&display={display}";

    private final static String GET_ACCESS_REFRESH_TOKEN = "https://graph.qq.com/oauth2.0/token?grant_type={refresh_token}" +
            "&client_id={client_id}&client_secret={client_secret}&refresh_token={refresh_token}";

    private final static String GET_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token?grant_type={refresh_token}" +
            "&client_id={client_id}&client_secret={client_secret}&refresh_token={refresh_token}&code={code}";

    private final static String GET_OPEN_ID = "https://graph.qq.com/oauth2.0/me?access_token={access_token}";

    private final static String GET_USER_INFO = "https://graph.qq.com/user/get_user_info?access_token={access_token}" +
            "&oauth_consumer_key={oauth_consumer_key}&openid={openid}&format=json";
    private static final QqServiceApi qqServiceApi = new QqServiceApi();

    private static final Gson gson = new Gson();

    public static UserActionService getThirdUserServiceImpl() {
        return qqServiceApi;
    }

    @Override
    public Object thirdInfo(String accessToken, String appSecret, String openid, String appId, UserPo userPo) {
        QqServiceApi.QqUserInfo usersShow = QqServiceApi.getUsersShow(accessToken, openid, appId);
        //注册
        userPo.setNickName(usersShow.getNickname());
        userPo.setRegType(4);
        userPo.setBeVerified(1);
        userPo.setHeadPortrait(usersShow.getFigureurl_qq_2());
        userPo.setLoginToken(accessToken);
        userPo.setLoginName(openid);
        userPo.setAccountType(AccountType.QQ_ACCOUNT);
        userPo.setLoginType(LoginType.QQ_LOGIN);
        userPo.setLoginName(openid);
        userPo.setLoginToken(accessToken);
        userPo.setRegType(AccountType.QQ_ACCOUNT.getType());
        return userPo;
    }

//    @Override
//    public Object bind(String accessToken, String appSecret, String openid, String appId, UserPo userPo) {
//        Long profileId = userPo.getProfileId();
//        UserProfile userProfile = userProfileDao.queryById(profileId);
//        Long accountId = userProfile.getAccountId();
//        if(null != accountId && accountId > 0){
//            UserAccount userAccount = userAccountDao.queryById(accountId);
//            if(null != userAccount && userAccount.getAccountStatus().equals(Const.STATUS_NORMAL)){
//                QqUserInfo usersShow = getUsersShow(accessToken, openid, appId);
//                ThirdUserInfo thirdUserInfo = new ThirdUserInfo();
//                thirdUserInfo.setAccountId(accountId);
//                thirdUserInfo.setThirdType(ThirdPatryType.qq.getType());
//                thirdUserInfo.setLoginName(openid);
//                thirdUserInfo.setThirdToken(accessToken);
//                thirdUserInfo.set
//            }
//        }
//
//        return null;
//    }

    public static OpenId getOpenId(String token) {
        OpenId result = new OpenId();
        String openIdUrl = MatchStringUtil.genStr("", GET_OPEN_ID, CollectionBuilder.mapBuilder.put("access_token", token).getMap());

        result = gson.fromJson(HttpKit.get(openIdUrl), OpenId.class);
        if (null == result.getOpenid()) {
            throw new AppRuntimeException(InfoCode.SYSTEM_ERROR);
        }
        return result;
    }

    public static String refreshToken(String token, String appId, String appSecret) {
        String result = null;
        String refreshToken = MatchStringUtil.genStr("", GET_ACCESS_REFRESH_TOKEN, CollectionBuilder.mapBuilder
                .put("grant_type", "refresh_token")
                .put("client_id", appId)
                .put("client_secret", appSecret)
                .put("refresh_token", token)
                .getMap());
        result = HttpKit.get(refreshToken);
        if (null == result) {
            throw new AppRuntimeException(InfoCode.SYSTEM_ERROR);
        }
        return result;
    }

    public static QqUserInfo getUsersShow(String accessToken, String openid, String appId) {
//        Optional.of(access_token);
//        Optional.of(openId);
        QqUserInfo result = new QqUserInfo();
        String userInfoUrl = MatchStringUtil.genStr("", GET_USER_INFO, CollectionBuilder.mapBuilder
                .put("access_token", accessToken)
                .put("oauth_consumer_key", appId)
                .put("openid", openid)
                .getMap());
        result = gson.fromJson(HttpKit.get(userInfoUrl),QqUserInfo.class);
        if (null == result.getNickname()) {
            throw new AppRuntimeException(InfoCode.SYSTEM_ERROR);
        }
        return result;
    }

    public static String getAccessToken(String code, String appId, String appSecret, String domainName, String backUrl) {
        Optional.of(code);
        String result = null;
        String accessTokenUrl = MatchStringUtil.genStr("", GET_ACCESS_TOKEN, CollectionBuilder.mapBuilder
                .put("grant_type", GRANT_TYPE)
                .put("client_id", appId)
                .put("client_secret", appSecret)
                .put("code", code)
                .put("redirect_uri", domainName + backUrl)
                .getMap());
        result = HttpKit.get(accessTokenUrl);
        if (null == result) {
            throw new AppRuntimeException(InfoCode.SYSTEM_ERROR);
        }
        return result;
    }


    public static class QqUserInfo {
        private Integer ret;
        private String msg, nickname, gender, figureurl_qq_2;

        @Override
        public String toString() {
            return "QqUserInfo{" +
                    "ret=" + ret +
                    ", msg='" + msg + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", gender='" + gender + '\'' +
                    ", figureurl_qq_2='" + figureurl_qq_2 + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            QqUserInfo that = (QqUserInfo) o;
            return Objects.equals(ret, that.ret) &&
                    Objects.equals(msg, that.msg) &&
                    Objects.equals(nickname, that.nickname) &&
                    Objects.equals(gender, that.gender) &&
                    Objects.equals(figureurl_qq_2, that.figureurl_qq_2);
        }

        @Override
        public int hashCode() {

            return Objects.hash(ret, msg, nickname, gender, figureurl_qq_2);
        }

        public String getFigureurl_qq_2() {
            return figureurl_qq_2;
        }

        public void setFigureurl_qq_2(String figureurl_qq_2) {
            this.figureurl_qq_2 = figureurl_qq_2;
        }

        public Integer getRet() {
            return ret;
        }

        public QqUserInfo setRet(Integer ret) {
            this.ret = ret;
            return this;
        }

        public String getMsg() {
            return msg;
        }

        public QqUserInfo setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public String getNickname() {
            return nickname;
        }

        public QqUserInfo setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public String getGender() {
            return gender;
        }

        public QqUserInfo setGender(String gender) {
            this.gender = gender;
            return this;
        }


    }

    public static class OpenId {
        public String client_id;
        public String openid;

        public String getClient_id() {
            return client_id;
        }

        public OpenId setClient_id(String client_id) {
            this.client_id = client_id;
            return this;
        }

        public String getOpenid() {
            return openid;
        }

        public OpenId setOpenid(String openid) {
            this.openid = openid;
            return this;
        }
    }

}
