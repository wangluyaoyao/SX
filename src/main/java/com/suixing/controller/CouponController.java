package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.service.ICouponService;
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
public class CouponController {
    @Autowired
    private ICouponService iCouponService;

    //加载优惠券
    @GetMapping("getAllCoupon")
    public ServerResponse getAllCoupno(){
        ServerResponse response = iCouponService.getCouponAll();
        System.out.println(response);
        return response;
    }
}
