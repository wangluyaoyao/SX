package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.Order;
import com.suixing.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@RestController
@RequestMapping("/sx-order")
public class OrderController {
    @Autowired
    private IOrderService iOrderService;

    //订单确认页面绑定
    @GetMapping("/order_confirm/{ordNumber}")
    public ModelAndView selectByOrderNum(@PathVariable("ordNumber") Long ordNumber){
        ServerResponse result = iOrderService.getByOrderNum(ordNumber);
        ModelAndView mav = new ModelAndView();
        mav.addObject("result",result);
        mav.setViewName("order/order_confirm");
        return mav;
    }

    //下订单页面绑定
    @GetMapping("/order_drop/{ordId}")
    public ModelAndView selectById(@PathVariable("ordId") Integer ordId){
        ServerResponse result = iOrderService.getById(ordId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("result",result);
        mav.setViewName("order/order_drop");
        return mav;
    }

    @GetMapping("myorder")
    public ServerResponse getOrder(){
        ServerResponse result = iOrderService.getOrderAll();
        return result;
    }

//    @PostMapping("tomyorder")
//    public ServerResponse save(Order order){
//        order.setOrdCreateTime(new Date());
//        ServerResponse result = iOrderService.saveOrder(order);
//        return result;
//
//    }

}
