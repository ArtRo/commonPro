package com.sangrade.base.util.Constants;

public enum  InfoCode {

    SYSTEM_ERROR(404,"服务器异常"),

    SUCCESS(200,"请求成功"),


    NOT_SUPPORT_THIRD_PARTY_TYPES(1001,"不支持的三方登陆类型"),

    INADEQUATE_USER_INFORMATION(1002,"不合格的用户"),

    THE_BOUND_USER_DOES_NOT_EXIST(1003,"绑定用户不存在"),

    BINDING_FAILED(1004,"绑定失败"),

    LOGIN_FAILURE(1005,"登陆失效"),

    THE_LAST_ACCOUNT_CANNOT_BE_UNTIED(100000,"最后一个账号不能解绑");

    InfoCode(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
    Integer code;
    String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
