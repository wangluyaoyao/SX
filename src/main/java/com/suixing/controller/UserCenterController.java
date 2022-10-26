package com.suixing.controller;

import com.suixing.commons.ServerResponse;
import com.suixing.service.IUserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserCenterController {
    @Autowired
    private IUserCenterService userCenterService;

    @GetMapping("usercenter/user")
    @ResponseBody
    public ServerResponse getUserId(){
        ServerResponse user = userCenterService.getUserById(6);
        return user;
    }
}
