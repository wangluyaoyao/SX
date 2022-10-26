package com.suixing.controller;

import com.suixing.commons.ServerResponse;
import com.suixing.entity.User;
import com.suixing.service.IUserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

@Controller
@RequestMapping("/usercenter")
public class UserCenterController {
    @Autowired
    private IUserCenterService userCenterService;

    //个人中心中用户信息的显示
    @GetMapping("/user")
    @ResponseBody
    public ServerResponse getUserId(){
        ServerResponse user = userCenterService.getUserById(6);
        return user;
    }

    //个人中心中修改密码和修改用户信息
    @PostMapping("/user/update")
    @ResponseBody
    public ServerResponse updateUser(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Long userTel = Long.valueOf(request.getParameter("userTel"));
        String userPsd =request.getParameter("userPsd");
        String userGender =request.getParameter("userGender");
        String userIdcard =request.getParameter("userIdcard");
        String userName =request.getParameter("userName");
        String userEmail =request.getParameter("userEmail");
        LocalDate userBir = LocalDate.parse(request.getParameter("userBir"));
        String userPetname =request.getParameter("userPetname");
        User user = userCenterService.getUserUpdateById(6);
        user.setUserBir(userBir);
        user.setUserEmail(userEmail);
        user.setUserGender(userGender);
        user.setUserIdcard(userIdcard);
        user.setUserPsd(userPsd);
        user.setUserName(userName);
        user.setUserTel(userTel);
        user.setUserPetname(userPetname);
        ServerResponse result = userCenterService.updateUser(user);

        return result;
    }



}
