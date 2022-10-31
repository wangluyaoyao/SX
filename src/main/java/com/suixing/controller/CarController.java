package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.service.ICarService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@RestController
@RequestMapping("/Car")
public class CarController {
    @Autowired
    private ICarService carService;
    @GetMapping("getCarById/{page}")
    public ServerResponse getCarById(@PathVariable("page") Integer page){
        ServerResponse response = carService.getPage(page);
        System.out.println("查询到的数据："+response.getData());
        return response;
    }
    @GetMapping("getCarListByBrand")
    public ServerResponse getCarListByBrand(@RequestParam("brand") String brand,@RequestParam("page") Integer page){
        System.out.printf("aaaaa");
        carService.getCarListByBrand(brand);
        ServerResponse response = carService.scerrenPage(page);
        System.out.println("查询到的brand数据："+response.getData());
        return response;
    }
    @GetMapping("getCarBrandPage/{page}")
    public ServerResponse getCarBrandPage(@PathVariable("page") Integer page){
        ServerResponse response = carService.scerrenPage(page);
        System.out.println("查询到的数据："+response.getData());
        return response;
    }


}
