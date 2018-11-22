package com.sangrade.base.consumer.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sangrade.base.api.model.UserProfile;
import com.sangrade.base.api.service.UserProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/userProfileController")
public class UserProfileControllerController {

    @Reference
    private UserProfileService userProfileService;

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<UserProfile> list(@RequestBody UserProfile userProfile) {

        List<UserProfile> dataList = userProfileService.selectByEntity(userProfile);
        return dataList;
    }

    @RequestMapping(value = "/userInfo/{id}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserProfile getUserInfo(@PathVariable("id") Long id){
        return userProfileService.selectById(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody UserProfile userProfile) {

        return userProfileService.insert(userProfile);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public int update(@RequestBody UserProfile userProfile) {

        return userProfileService.updateByEntity(userProfile);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public int delete(@RequestBody Long id) {

        return userProfileService.deleteById(id);
    }
}