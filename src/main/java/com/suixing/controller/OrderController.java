package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.Bussiness;
import com.suixing.entity.Car;
import com.suixing.entity.Order;
import com.suixing.entity.UserCoupno;
import com.suixing.service.IBussinessService;
import com.suixing.service.ICarService;
import com.suixing.service.IOrderService;
import com.suixing.service.IUserCoupnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    //订单确认页面绑定
//    @GetMapping("/order_confirm/{ordId}")
//    public ModelAndView selectByOrderNum(@PathVariable("ordId") Integer ordId){
//        ServerResponse result = orderService.getById(ordId);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("result",result);
//        mav.setViewName("order/order_confirm");
//        return mav;
//    }

    //确认订单页面绑定
//    @GetMapping("/order_drop/{ordId}")
//    public ModelAndView selectById(@PathVariable("ordId") Integer ordId){
//        ServerResponse result = orderService.getById(ordId);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("result",result);
//        mav.setViewName("order/order_drop");
//        return mav;
//    }

//    @GetMapping("myorder")
//    public ServerResponse getOrder(){
//        ServerResponse result = iOrderService.getOrderAll();
//        return result;
//    }

    //确认订单页面获取车辆订单信息
    @GetMapping("dropOrder")
    public ModelAndView getInstance(Integer carId,
                                    Integer userCouId,
                                    Float ordFees,
                                    LocalDateTime ordPicTime,
                                    LocalDateTime ordDroTime){
        //1.车辆图片、名字、日租价格
        Car car = carService.getCarWithFewInfo(carId);
        //2.租车日期、还车日期、租期

        //3.租车网点、地址
        ServerResponse bussiness = carService.getBussiness(carId);
        //4.优惠券
        UserCoupno userCoupno = userCoupnoService.getCoupnoOwn(userCouId);
        //5.其它服务
        ModelAndView mav = new ModelAndView("order/drop_order");
        mav.addObject("car",car);
        mav.addObject("bussiness",bussiness);
        mav.addObject("userCoupno",userCoupno);
        mav.addObject("ordFees",ordFees);
        mav.setViewName("order/order_drop");
        return mav;
    }

    //创建订单
    @PostMapping("saveOrder")
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
        mav.setViewName("order/order_update");
        return mav;
    }

}
