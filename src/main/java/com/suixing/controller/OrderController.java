package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.*;
import com.suixing.service.*;
import com.suixing.util.TokenUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Controller
//@RequestMapping("/sx-order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICarService carService;
    @Autowired
    private IBussinessService bussinessService;
    @Autowired
    private IUserCenterService userCenterService;


    //订单确认页面绑定
//    @GetMapping("/order_confirm/{ordId}")
//    public ModelAndView selectByOrderNum(@PathVariable("ordId") Integer ordId){
//        ServerResponse result = orderService.getById(ordId);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("result",result);
//        mav.setViewName("order/order_confirm");
//        return mav;

// }


    //确认订单页面获取车辆订单信息
    @GetMapping("/dropOrder/{carId}")
    public ModelAndView getInstance(@PathVariable("carId") Integer carId,
                                    LocalDateTime ordPicTime,LocalDateTime ordDroTime){
        //1.车辆图片、名字、日租价格
        Car car = carService.getById(carId);
        //2.租车日期、还车日期、租期

        //3.租车网点、地址
        ServerResponse bussiness = bussinessService.getBussiness(car.getBusId());
        ModelAndView mav = new ModelAndView();
        mav.addObject("car",car);
        mav.addObject("bussiness",bussiness.getData());
        mav.setViewName("order/order_drop");
        System.out.println(car);
        return mav;
    }

    //优惠券
    @GetMapping("/coupon")
    @ResponseBody
    public ServerResponse getCouponByUserId(HttpServletRequest request){
        String token = request.getHeader("token");//get token
        Integer userId = TokenUtil.parseToken(token).getUserId();
        return userCenterService.getCoupon(userId);
    }

    //创建订单
    @RequestMapping(value = "/saveOrder", method = {RequestMethod.POST})
    @ResponseBody
    public ModelAndView saveOrder(Integer carId, Order order,Integer userId,Integer couId){
        //用户id
        System.out.println(1);
        /*String token = request.getHeader("token");//get token
        Integer userId = TokenUtil.parseToken(token).getUserId();*/

        System.out.println("用户id:"+userId);
        System.out.println("优惠券id"+couId);
        //订单编号
        Long ordNumber = UUID.randomUUID().getMostSignificantBits();
        if(ordNumber<0){
            ordNumber = -ordNumber;
        }
        //订单状态
        String ordSatus = "预约中";
        //创建订单时间
        Date ordCreateTime = new Date();
        //服务费
        Float ordServiceTip = 20f;
        order.setCarId(carId);
        order.setOrdNumber(ordNumber);
        order.setOrdSatus(ordSatus);
        order.setOrdCreateTime(ordCreateTime);
        order.setOrdServiceTip(ordServiceTip);
        order.setUserId(userId);
        if (couId !=null){
            order.setCouId(couId);
        }

        ServerResponse result = orderService.saveOrder(order);

        ModelAndView mav = new ModelAndView();
        System.out.println(222);
        mav.addObject(result);
        mav.setViewName("order/order_update");
        System.out.println(222333);
        return mav;

    }

}
