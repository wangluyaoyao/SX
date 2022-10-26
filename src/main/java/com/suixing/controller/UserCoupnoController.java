package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.User;
import com.suixing.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@RestController

public class UserCoupnoController {

//    优惠卷发放：用延迟队列和死信队列
    @PostMapping("userRecCou")
    //用户领取优惠卷
    public ServerResponse userRecCou(Integer userId,Integer couId){
        //判断如果没有领取

        return null;
    }


}
