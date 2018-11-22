package com.sangrade.base.util.Constants;

public enum  AccountType {

    ADMIN_ACCOUNT(1,"管理员账号"),

    VIP_ACCOUNT(2,"vip账号"),

    WX_ACCOUNT(3,"微信账号"),

    QQ_ACCOUNT(4,"qq账号"),

    WB_ACCOUNT(5,"微博账号"),

    OTHER_THIRD_ACCOUNT(6,"三方其他账号"),

    COMMON_ACCOUNT(0,"普通账号");


    AccountType(Integer type,String desc){
        this.type = type;
        this.desc = desc;
    }
    Integer type;
    String desc;

    public static AccountType getEnumByType(Integer type){
        if(null != type){
            for(AccountType ly : AccountType.values()){
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
