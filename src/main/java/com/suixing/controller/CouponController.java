package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private ICouponService iCouponService;

    @GetMapping("getCoupon")
    @ResponseBody
    public ServerResponse getCarById(){
        ServerResponse response = iCouponService.getCouponAll();
        return response;
    }
}
