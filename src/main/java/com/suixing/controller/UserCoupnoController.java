package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.User;
import com.suixing.service.ICarService;
import com.suixing.service.IUserCoupnoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private IUserCoupnoService userCoupnoService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

//    优惠卷发放：用延迟队列和死信队列
    @GetMapping("userRecCoupon")
    //用户领取优惠卷
    public ServerResponse userRecCou(Integer userId,Integer couId){
        ServerResponse serverResponse = userCoupnoService.userRecCou(userId,couId);//redis
         rabbitTemplate.convertAndSend("couponDrawDirectExchange","qeqe12138",couId);
    //    userCoupnoService.sendMsg(couId);//发送消息到rabbitmq修改数据库信息
        Map<String,Object> map = new HashMap<>();
        map.put("coupon",serverResponse.getData());
        map.put("userId",userId);
        //给主页发送领取成功的消息
        System.out.println(map);
      //  rabbitTemplate.convertAndSend("sendMsgDirectExchange","12138GGGGG",map);
        rabbitTemplate.convertAndSend("delayed-exchange","delay",map,message -> {
            message.getMessageProperties().setDelay(10000);
            return message;
        });
        return serverResponse;
    }



}
