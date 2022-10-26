package com.suixing.controller;


import com.suixing.entity.User;
import com.suixing.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.schema.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Controller
@RequestMapping("/sx-user")
public class UserController {

//    植入对象
    @Resource(name = "iUserService")
    private IUserService service;

//    返回页面
    @RequestMapping("/get-login")
    public String getLogin(){
        return "customer/login";
    }

//    登录

    @RequestMapping("/login")
    public ModelAndView login(User user, ModelAndView mv, HttpServletRequest request, Model model){
        User login = service.login(user.getUserTel(),user.getUserPsd());
        System.out.println(login);
        if (login!=null){
            request.getSession().setAttribute("login",login);
            System.out.println("成功！！");
            mv.setViewName("index");
        }else {
            System.out.println("失败。。。");
            mv.setViewName("customer/login");
        }
        return mv;
    }
}
