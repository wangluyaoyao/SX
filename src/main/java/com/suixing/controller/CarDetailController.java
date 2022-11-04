package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.*;
import com.suixing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("Car")
//@RequestMapping("sx-details")
public class CarDetailController {
    @Autowired
    private IDetailService detailService;
    @Autowired
    private ICarService carService;
    @Autowired
    private ICommentsService commentsService;
    @Autowired
    private IReplyService replyService;
    @Autowired
    private IBussinessService bussinessService;
    @Autowired
    private IUserService userService;
//    @GetMapping("details")
//    public ModelAndView getCarDetail(@PathVariable("carId") Integer carId){
//        ServerResponse serverResponse = detailService.getDetails(carId);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("serverResponse",serverResponse);
//        modelAndView.setViewName("details/details_of_cars");
//        return modelAndView;
//    }
//    @GetMapping("details/{carId}")
//    @ResponseBody
//    public Car selectCarById(@PathVariable("carId")Integer carId){
//        return detailService.getCarById(carId);
//    }
    @GetMapping("/details/{carId}")
    public ModelAndView getCarDetail(@PathVariable("carId")Integer carId){
        System.out.println(carId);
        Car car = carService.getById(carId);
        System.out.println("1");
        System.out.println(car.getBusId());
        System.out.println(car);
        //网点信息

        Bussiness bussiness = bussinessService.getBussinessWithInfo(car.getBusId());
        System.out.println(bussiness);


        Comments comments =commentsService.getCommentsByCarId(carId);
        System.out.println(comments);

        Reply reply = replyService.getReplyByCommId(carId);
        System.out.println(reply);

        User commuser = commentsService.getUserByCommId(carId);
        System.out.println(commuser);
        User replyuser = replyService.getUserByReplyId(carId);
        System.out.println(replyuser);

        ModelAndView mav = new ModelAndView();
        mav.addObject("car",car);
        mav.addObject("bussiness",bussiness);
        mav.addObject("comments",comments);
        mav.addObject("reply",reply);
        mav.addObject("replyuser",replyuser);
        mav.addObject("commuser",commuser);
        //mav.addObject("bussiness",bussiness.getData());
        //mav.addObject("userCoupno",userCoupno);
        //mav.addObject("ordFees",ordFees);
        mav.setViewName("details/details_of_cars");
        System.out.println(car);
        return mav;
    }

    @GetMapping("/details/user")
    public ServerResponse getUserDetail(HttpServletRequest request){

        return null;
    }

}
