package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.Order;
import com.suixing.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Controller
@RequestMapping("/sx-order")
public class OrderController {
    @Autowired
    private IOrderService iOrderService;

    @GetMapping("order")
    @ResponseBody
    public ServerResponse selectOrderAll(){
        ServerResponse result = iOrderService.selectOrderAll();
        return result;
    }

    @GetMapping("order/{ordNumber}")
    @ResponseBody
    public ServerResponse selectByOrderNum(Long ordNumber){
        ServerResponse result = iOrderService.selectByOrderNum(ordNumber);
        return result;
    }

    @PostMapping("order")
    @ResponseBody
    public ServerResponse saveOrder(Order order){
        return null;
    }

}
