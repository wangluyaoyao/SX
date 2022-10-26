package com.suixing;

import com.suixing.commons.ServerResponse;
import com.suixing.entity.Car;
import com.suixing.mapper.CarMapper;
import com.suixing.service.ICarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SuixingzucheApplicationTests {

    @Autowired
    private ICarService service;
    @Test
    void contextLoads() {

    }

    @Test
    public void selectById(){
        Car sxCar = service.selectId(1);
        System.out.println(sxCar);
    }



}
