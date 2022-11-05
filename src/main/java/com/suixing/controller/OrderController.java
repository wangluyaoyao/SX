package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.*;
import com.suixing.service.*;
import com.suixing.util.TokenUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @GetMapping(value ="/dropOrder" )
    @ResponseBody
    public ModelAndView getInstance(@RequestParam("carId") Integer carId,
                                    @RequestParam("start") String start,
                                    @RequestParam("end") String end){

        //1.车辆图片、名字、日租价格
        Car car = carService.getById(carId);
        //2.租车日期、还车日期、租期
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ordPicTime = LocalDateTime.parse(start,df);

        LocalDateTime ordDroTime = LocalDateTime.parse(end,df);
        Duration duration = Duration.between(ordPicTime,ordDroTime);
        Long ordLease = duration.toDays(); //相差的天数

        System.out.println(ordDroTime);
        System.out.println(ordPicTime);
        System.out.println(ordLease);

        //3.租车网点、地址
        ServerResponse bussiness = bussinessService.getBussiness(car.getBusId());
        ModelAndView mav = new ModelAndView();
        mav.addObject("car",car);
        mav.addObject("bussiness",bussiness.getData());
        mav.addObject("start",start);
        mav.addObject("end",end);
        mav.addObject("ordDroTime",start);
        mav.addObject("ordPicTime",end);
        mav.addObject("ordLease",ordLease);
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
    @PostMapping( "/saveOrder")
    @ResponseBody
    public ModelAndView saveOrder(Integer carId,Order order,Integer userId,Integer couId,Float ordCouMoney){

//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime start = LocalDateTime.parse(ordPicTime,df);
//
//        LocalDateTime end = LocalDateTime.parse(ordDroTime,df);

//        Order order = new Order();

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
        order.setOrdCouMoney(ordCouMoney);
        order.setOrdServiceTip(ordServiceTip);
        order.setUserId(userId);
//        order.setOrdPicTime(start);
//        order.setOrdDroTime(end);
//        order.setOrdLease(ordLease);
        if (couId !=null){
            order.setCouId(couId);
        }
        ServerResponse result = orderService.saveOrder(order);
        System.out.println(order);
        System.out.println(result);
        ModelAndView mav = new ModelAndView();
        mav.addObject(result);
        mav.setViewName("order/order_update");
        return mav;
    }



    /*还车按钮*/
    @PostMapping("/successOrder")
    @ResponseBody
    public ServerResponse successOrder(Long ordNumber){
        ServerResponse response = orderService.orderStatusSccess(ordNumber);
        return response;
    }







}
