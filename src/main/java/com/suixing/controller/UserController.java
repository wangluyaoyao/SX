package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.LoginCustomer;
import com.suixing.entity.User;
import com.suixing.service.IUserService;
import com.suixing.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.schema.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */

@RestController
@RequestMapping("/customer")
public class UserController {

//    植入对象


    @Autowired
    private IUserService userService;

    @PostMapping("login")

    public ServerResponse login(User user,HttpServletRequest request,HttpServletResponse response){
        ServerResponse result = userService.login(user);
        System.out.println("controller login response:"+request);
        if (result.getResultcode() == 200){
            String token = (String) result.getData();
            System.out.println("customer controller 登陆成功");
        }
        return result;
    }
    //    页面验证显示用户名
    @PostMapping("userVerification/{token}")
    public ServerResponse userVerification(@PathVariable("token") String token){
        System.out.println("token:"+token);
        LoginCustomer loginCustomer = null;
        String loginUserName = null;
        if (token!=null){
            loginCustomer = TokenUtil.parseToken(token);
            loginUserName =   loginCustomer.getUserName();
        }
        System.out.println("loginUserName:"+loginUserName);
        return ServerResponse.success("查询成功",loginCustomer);
    }



}
