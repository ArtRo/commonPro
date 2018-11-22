package com.sangrade.base.util.error;

import com.sangrade.base.util.Constants.InfoCode;

public class AppRuntimeException extends RuntimeException{
    Integer errorCode;
    String errorMsg;
    public AppRuntimeException(){}

    public AppRuntimeException(Integer errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public AppRuntimeException(InfoCode infoCode){
        this(infoCode.getCode(),infoCode.getMsg());
    }
}
