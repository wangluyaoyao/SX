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
//    @GetMapping("/order_confirm/{ordNumber}")
//    public ModelAndView selectByOrderNum(@PathVariable("ordNumber") Long ordNumber){
//        ServerResponse result = iOrderService.getByOrderNum(ordNumber);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("result",result);
//        mav.setViewName("order/order_confirm");
//        return mav;
//    }

    //下订单页面绑定
//    @GetMapping("/order_drop/{ordId}")
//    public ModelAndView selectById(@PathVariable("ordId") Integer ordId){
//        ServerResponse result = iOrderService.getById(ordId);
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

    @PostMapping("dropOrder")
    public ModelAndView saveOrder(Integer carId,Integer busId,Integer userCouId,Float ordFees,Float ordServiceTip){
        //1.车辆图片、名字、日租价格
        Car car = carService.getCarWithFewInfo(carId);

        //2.租车日期、还车日期、租期

        //3.租车网点、地址
        Bussiness bussiness =bussinessService.getBussinessWithInfo(busId);

        //4.优惠券
        UserCoupno userCoupno = userCoupnoService.getCoupnoOwn(userCouId);

        //5.其它服务
        ModelAndView mav = new ModelAndView("dropOrder");
//        mav.addObject("car",car);
        mav.addObject("bussiness",bussiness);
        mav.addObject("userCoupno",userCoupno);
        mav.addObject("ordFees",ordFees);
        mav.addObject("ordServiceTip",ordServiceTip);


        return mav;
    }

}
