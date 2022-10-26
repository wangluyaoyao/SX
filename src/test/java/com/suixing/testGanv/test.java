package com.suixing.testGanv;

import com.suixing.commons.ServerResponse;
import com.suixing.service.ICarService;
import com.suixing.service.ICouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    @Autowired
    private ICarService service;
    @Autowired
    private ICouponService copservice;
    @Test
    public void getAll(){
        System.out.println(service.getCarAll().getData());
    }

    @Test
    public void getPage(){
        ServerResponse serverResponse =  service.getPage(1);
        Object list =serverResponse.getData();
        System.out.println(list);
    }
    @Test
    public void getCoupno(){
        System.out.println(copservice.getCouponAll());
    }
}
