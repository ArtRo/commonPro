package com.sangrade.base.api.third.weixi;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WxPayService {

    void wxNotify(HttpServletRequest request, HttpServletResponse response);


}
