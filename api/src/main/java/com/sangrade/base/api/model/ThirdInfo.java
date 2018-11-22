package com.sangrade.base.api.model;

import java.io.Serializable;
import java.util.Date;

/* 读取表结构信息 */
public class ThirdInfo implements Serializable {
    
    public ThirdInfo() {

    }
    
    /*   */
    private Long id;
    /*   */
    private Long accountId;
    /* 用户三方的唯一标识  */
    private String openId;
    /* 0 正常 -1 删除  */
    private Integer isDel;
    /*   */
    private Date createTime;
    /* 三方反馈的信息  */
    private String infoDesc;
    /* 昵称  */
    private String nickName;
    /*   */
    private Date updateTime;
    /* 三方类型  */
    private Integer thirdType;
    
    public Long getId() {
        return this.id;
    }
    public Long getAccountId() {
        return this.accountId;
    }
    public String getOpenId() {
        return this.openId;
    }
    public Integer getIsDel() {
        return this.isDel;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public String getInfoDesc() {
        return this.infoDesc;
    }
    public String getNickName() {
        return this.nickName;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public Integer getThirdType() {
        return this.thirdType;
    }
   
    public void setId(Long id) {
        this.id = id;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void setInfoDesc(String infoDesc) {
        this.infoDesc = infoDesc;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public void setThirdType(Integer thirdType) {
        this.thirdType = thirdType;
    }
  
}