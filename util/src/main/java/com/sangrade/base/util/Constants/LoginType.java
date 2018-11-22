package com.sangrade.base.util.Constants;

public enum  LoginType {

    PHONE_MSG(1,"手机短信登陆"),

    WX_LOGIN(2,"微信登陆"),

    QQ_LOGIN(3,"QQ登陆"),

    WEIBO_LOGIN(4,"微博登陆"),

    OTHER_LOGIN(5,"邮箱登陆"),

    PHONE_LOGIN(6,"手机登陆"),

    ORTHER_LOGIN(7,"其他方式登陆"),

    ACCOUNT_PASS(0,"账号密码登陆");

    LoginType(Integer type,String desc){
        this.type = type;
        this.desc = desc;
    }
    Integer type;
    String desc;

    public static LoginType getEnumByType(Integer type){
        if(null != type){
            for(LoginType ly : LoginType.values()){
                ly.type.equals(type);
                return ly;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
