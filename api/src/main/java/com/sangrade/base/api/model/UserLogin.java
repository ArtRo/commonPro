package com.sangrade.base.api.model;

import java.io.Serializable;

/* 读取表结构信息 */
public class UserLogin implements Serializable{
    
    public UserLogin() {

    }
    
    /*   */
    private Long id;
    /* 用户app标识  */
    private String token;
    /* 过期时间  */
    private Long expireTime;
    /* 用户账号ID  */
    private Long accountId;
    /* 用户三方的唯一标识  */
    private String openId;
    
    public Long getId() {
        return this.id;
    }
    public String getToken() {
        return this.token;
    }
    public Long getExpireTime() {
        return this.expireTime;
    }
    public Long getAccountId() {
        return this.accountId;
    }
    public String getOpenId() {
        return this.openId;
    }
   
    public void setId(Long id) {
        this.id = id;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
  
}