package com.sangrade.base.consumer.third.paid;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sangrade.base.api.third.weixi.WxPayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(name = "三方支付有关的回调接口",value = "/thirdPaidRollBack")
public class RollBackController {

    @Reference
    WxPayService wxPayService;

    @RequestMapping(name = "微信扫码二维码支付回调", value = "/uniCodeRollBack", produces = "application/json; charset=utf-8")
    @ResponseBody
    public void wxnotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
         wxPayService.wxNotify(request, response);
    }


}
