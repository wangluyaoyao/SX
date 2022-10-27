package com.suixing.testGanv;

import com.suixing.commons.ServerResponse;
import com.suixing.service.IBussinessService;
import com.suixing.service.IUserCenterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testUserCenter {
    @Autowired
    private IUserCenterService userCenterService;

    @Autowired
    private IBussinessService bussinessService;



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

}
