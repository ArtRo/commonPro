package com.sangrade.base.api.model;


import java.io.Serializable;

/* 读取表结构信息 */
public class ThirdProfile implements Serializable {
    
    public ThirdProfile() {

    }
    
    /*   */
    private Long id;
    /* 三方名称  */
    private String name;
    /*   */
    private String accountKey;
    /*   */
    private String accountSecret;
    /*   */
    private String domainName;
    /*   */
    private String openAccountKey;
    /*   */
    private String openAccountValue;
    /*   */
    private String callBackUrl;
    /*   */
    private String endPoint;
    /*   */
    private Integer isDel;
    
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getAccountKey() {
        return this.accountKey;
    }
    public String getAccountSecret() {
        return this.accountSecret;
    }
    public String getDomainName() {
        return this.domainName;
    }
    public String getOpenAccountKey() {
        return this.openAccountKey;
    }
    public String getOpenAccountValue() {
        return this.openAccountValue;
    }
    public String getCallBackUrl() {
        return this.callBackUrl;
    }
    public String getEndPoint() {
        return this.endPoint;
    }
    public Integer getIdDel() {
        return this.isDel;
    }
   
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }
    public void setAccountSecret(String accountSecret) {
        this.accountSecret = accountSecret;
    }
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    public void setOpenAccountKey(String openAccountKey) {
        this.openAccountKey = openAccountKey;
    }
    public void setOpenAccountValue(String openAccountValue) {
        this.openAccountValue = openAccountValue;
    }
    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
  
}