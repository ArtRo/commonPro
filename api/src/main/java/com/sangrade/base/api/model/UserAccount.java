package com.sangrade.base.api.model;

import java.io.Serializable;
import java.util.Date;

/* 读取表结构信息 */
public class UserAccount implements Serializable {
    
    public UserAccount() {

    }
    
    /*   */
    private Long id;
    /*   */
    private Integer isDel;
    /* 账号类型 0 普通账号  */
    private Integer type;
    /* 账号状态 0 正常  */
    private Integer status;
    /*   */
    private Date createTime;
    /*   */
    private Date updateTime;
    /* 用户账号  */
    private String name;
    /* 账号密码  */
    private String password;
    
    public Long getId() {
        return this.id;
    }
    public Integer getIsDel() {
        return this.isDel;
    }
    public Integer getType() {
        return this.type;
    }
    public Integer getStatus() {
        return this.status;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
   
    public void setId(Long id) {
        this.id = id;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
  
}