package com.suixing.car;

import com.suixing.entity.Car;
import com.suixing.mapper.CarMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    private CarMapper sxCarMapper;
    @Test
    public void selectById(){
        Car sxCar = sxCarMapper.selectById(1);
        System.out.println(sxCar);
    }
}
