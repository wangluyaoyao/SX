package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.*;
import com.suixing.service.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
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
    private IOrderService orderService;

    @Autowired
    private ICarService carService;

    @Autowired
    private IBussinessService bussinessService;

    @Autowired
    private IUserCoupnoService userCoupnoService;

    @Autowired
    private ICouponService couponService;

    //订单确认页面绑定
//    @GetMapping("/order_confirm/{ordId}")
//    public ModelAndView selectByOrderNum(@PathVariable("ordId") Integer ordId){
//        ServerResponse result = orderService.getById(ordId);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("result",result);
//        mav.setViewName("order/order_confirm");
//        return mav;
//    }

    //确认订单页面获取车辆订单信息
    @GetMapping("dropOrder/car/{carId}")
//    @ResponseBody
    public ModelAndView getInstance(@PathVariable("carId") Integer carId,@PathVariable("userId") Integer userId){
        //1.车辆图片、名字、日租价格
        Car car = carService.getById(carId);
        //2.租车日期、还车日期、租期

        //3.租车网点、地址
        ServerResponse bussiness = bussinessService.getBussiness(car.getBusId());

        //4.优惠券
        UserCoupno userCoupno = userCoupnoService.getById(userId);
        ServerResponse coupon = couponService.getCouponOwn(userCoupno.getCouId()) ;
        System.out.println(coupon);

        ModelAndView mav = new ModelAndView();
        mav.addObject("car",car);
        mav.addObject("bussiness",bussiness.getData());
        mav.addObject("coupon",coupon);
        mav.setViewName("order/order_drop");
        System.out.println(car);
        return mav;
    }


    //创建订单
    @PostMapping("saveOrder")
//    @ResponseBody
    public ModelAndView saveOrder(Integer carId,
                                  Integer userCouId,
                                  Float ordFees,
                                  Float ordServiceTip,
                                  LocalDateTime ordPicTime,
                                  LocalDateTime ordDroTime,
                                  Integer ordLease,
                                  Float ordPrice){
        ModelAndView mav = new ModelAndView();
        mav.addObject("carId",carId);
        mav.addObject("userCouId",userCouId);
        mav.addObject("ordFees",ordFees);
        mav.addObject("ordServiceTip",ordServiceTip);
        mav.addObject("ordPicTime",ordPicTime);
        mav.addObject("ordDroTime",ordDroTime);
        mav.addObject("ordLease",ordLease);
        mav.addObject("ordPrice",ordPrice);
        mav.setViewName("/order/order_update");
        return mav;
    }

}
