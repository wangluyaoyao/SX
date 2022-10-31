package com.suixing.testGanv;

import com.suixing.commons.ServerResponse;
import com.suixing.entity.Coupon;
import com.suixing.entity.UserCoupno;
import com.suixing.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testUserCenter {
    @Autowired
    private IUserCenterService userCenterService;

    @Autowired
    private IBussinessService bussinessService;
    @Autowired
    private ICarService carService;
    @Autowired
    private IUserCoupnoService userCoupnoService;
    @Autowired
    private ICouponService couponService;



    @Test
    public void getUserById(){
        ServerResponse response = userCenterService.getUserById(6);
        System.out.println(response);
    }

    @Test
    public void getBuseAll(){
        ServerResponse response = bussinessService.getAll();
        System.out.println(response);
    }

    @Test
    public void getCoupByUserId(){
        ServerResponse response = userCenterService.getUserCoupon(6);
        System.out.println(response);
        response.getData().toString();
    }
    @Test
    public void getBuseid(){
        ServerResponse response = bussinessService.getBussiness(3);
        System.out.println(response.getData());
    }
    @Test
    public void getCar(){
        ServerResponse response = carService.getById(101);
        System.out.println(response.getData());
    }
    @Test
    public void getById(){
        ServerResponse response = couponService.getCouponOwn(1);
        System.out.println(response.getData());
    }

}
