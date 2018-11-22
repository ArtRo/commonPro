package com.sangrade.base.provider.third.WB;

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

public class WeiboServiceApi implements UserActionService {

    private final static Logger logger = LoggerFactory.getLogger(WeiboServiceApi.class);

    private final static String GET_ACCESS_TOKEN = "https://api.weibo.com/oauth2/access_token?client_id={client_id}&client_secret={client_secret}" +
            "&grant_type={grant_type}&code={code}&redirect_uri={redirect_uri}";
    private final static String GET_USER_INFO = "https://api.weibo.com/2/users/show.json?access_token={access_token}&uid={uid}";
    private static final WeiboServiceApi weiXinServiceApi = new WeiboServiceApi();

    private static final Gson gson = new Gson();

    public static UserActionService getThirdUserServiceImpl() {
        return weiXinServiceApi;
    }

    @Override
    public Object thirdInfo(String accessToken, String appSecret, String openid, String appId, UserPo userPo) {
        WeiboUserInfo usersShow = getUsersShow(accessToken, openid);
        //注册
        userPo.setNickName(usersShow.getScreen_name());
        userPo.setRegType(4);
        userPo.setBeVerified(1);
        userPo.setHeadPortrait(usersShow.getProfile_image_url());
        userPo.setLoginToken(accessToken);
        userPo.setLoginName(openid);
        userPo.setAccountType(AccountType.QQ_ACCOUNT);
        userPo.setLoginType(LoginType.QQ_LOGIN);
        userPo.setRegType(AccountType.QQ_ACCOUNT.getType());
        userPo.setLoginName(openid);
        userPo.setLoginToken(accessToken);
        return userPo;
    }

    public static WeiboUserInfo getUsersShow(String access_token, String uid) {
        Optional.of(access_token);
        Optional.of(uid);
        WeiboUserInfo result = new WeiboUserInfo();
        String userShowUrl = MatchStringUtil.genStr("", GET_USER_INFO, CollectionBuilder.mapBuilder
                .put("access_token", access_token)
                .put("uid", uid)
                .getMap());
        result = gson.fromJson(HttpKit.get(userShowUrl), WeiboUserInfo.class);
        if (null == result.getScreen_name()) {
            throw new AppRuntimeException(InfoCode.SYSTEM_ERROR);
        }
        return result;
    }

    public static AccessToken getAccessToken(String code, String appId, String appSecret, String domainName, String callBackUrl) {
        Optional.of(code);
        AccessToken result = new AccessToken();
        String accessTokenUrl = MatchStringUtil.genStr("", GET_ACCESS_TOKEN, CollectionBuilder.mapBuilder
                .put("client_id", appId)
                .put("client_secret", appSecret)
                .put("grant_type", GRANT_TYPE)
                .put("code", code)
                .put("redirect_uri", domainName + callBackUrl)
                .getMap());
        result =  gson.fromJson(HttpKit.get(accessTokenUrl),AccessToken.class);
        if (null == result.getAccess_token()) {
            throw new AppRuntimeException(InfoCode.SYSTEM_ERROR);
        }
        return result;
    }

    static class AccessToken {
        private String access_token, expires_in, uid;

        public String getAccess_token() {
            return access_token;
        }

        public AccessToken setAccess_token(String access_token) {
            this.access_token = access_token;
            return this;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public AccessToken setExpires_in(String expires_in) {
            this.expires_in = expires_in;
            return this;
        }

        public String getUid() {
            return uid;
        }

        public AccessToken setUid(String uid) {
            this.uid = uid;
            return this;
        }
    }

    static class WeiboUserInfo {
        private Long id;
        private String screen_name, description, profile_image_url, url, gender, avatar_large, error_code, request, error;

        public Long getId() {
            return id;
        }

        public WeiboUserInfo setId(Long id) {
            this.id = id;
            return this;
        }

        public String getScreen_name() {
            return screen_name;
        }

        public void setScreen_name(String screen_name) {
            this.screen_name = screen_name;
        }

        public String getDescription() {
            return description;
        }

        public WeiboUserInfo setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getProfile_image_url() {
            return profile_image_url;
        }

        public WeiboUserInfo setProfile_image_url(String profile_image_url) {
            this.profile_image_url = profile_image_url;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public WeiboUserInfo setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getGender() {
            return gender;
        }

        public WeiboUserInfo setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public String getAvatar_large() {
            return avatar_large;
        }

        public WeiboUserInfo setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
            return this;
        }

        public String getError_code() {
            return error_code;
        }

        public WeiboUserInfo setError_code(String error_code) {
            this.error_code = error_code;
            return this;
        }

        public String getRequest() {
            return request;
        }

        public WeiboUserInfo setRequest(String request) {
            this.request = request;
            return this;
        }

        public String getError() {
            return error;
        }

        public WeiboUserInfo setError(String error) {
            this.error = error;
            return this;
        }

        @Override
        public String toString() {
            return "WeiboUserInfo{" +
                    "id=" + id +
                    ", screen_name='" + screen_name + '\'' +
                    ", description='" + description + '\'' +
                    ", profile_image_url='" + profile_image_url + '\'' +
                    ", url='" + url + '\'' +
                    ", gender='" + gender + '\'' +
                    ", avatar_large='" + avatar_large + '\'' +
                    ", error_code='" + error_code + '\'' +
                    ", request='" + request + '\'' +
                    ", error='" + error + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WeiboUserInfo that = (WeiboUserInfo) o;
            return Objects.equals(id, that.id) &&
                    Objects.equals(screen_name, that.screen_name) &&
                    Objects.equals(description, that.description) &&
                    Objects.equals(profile_image_url, that.profile_image_url) &&
                    Objects.equals(url, that.url) &&
                    Objects.equals(gender, that.gender) &&
                    Objects.equals(avatar_large, that.avatar_large) &&
                    Objects.equals(error_code, that.error_code) &&
                    Objects.equals(request, that.request) &&
                    Objects.equals(error, that.error);
        }

        @Override
        public int hashCode() {

            return Objects.hash(id, screen_name, description, profile_image_url, url, gender, avatar_large, error_code, request, error);
        }
    }

}
