package com.suixing.testGanv;

import com.suixing.commons.ServerResponse;
import com.suixing.service.IBussinessService;
import com.suixing.service.IFlowService;
import com.suixing.service.IOrderService;
import com.suixing.service.IUserCenterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class testUserCenter {
    @Autowired
    private IUserCenterService userCenterService;

    @Autowired
    private IBussinessService bussinessService;

    @Autowired
    private IFlowService flowService;
    @Autowired
    private IOrderService orderService;


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
    public void getNum(){
        double v = Math.random()*1000000000;
        System.out.println(Math.round(v));
    }

    @Test
    public void getOrder(){
        ServerResponse response = orderService.getById(5);
        System.out.println(response.getData());

    }
    @Test
    public void saveFlow(){
        Map<String,Object> map = flowService.saveFlow(953L,"44552211",452.1f);
        System.out.println(map.get("orderNum"));
        System.out.println(map.get("flowNum"));
        System.out.println(map.get("flowPay"));
        System.out.println(map.get("carName"));
    }

    @Test
    public void getAllOrder(){
        ServerResponse response = userCenterService.getUserOrderAll(6);
        System.out.println(response.getData());
    }



}
