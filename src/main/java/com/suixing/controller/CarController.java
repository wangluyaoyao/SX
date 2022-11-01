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
    @GetMapping("getCarByPage/{page}")
    public ServerResponse getCarByPage(Integer page){
        ServerResponse response = carService.getPage(page);
        System.out.println("查询到的数据："+response.getData());
        return response;
    }
    @GetMapping("getCarByPage/{page}/{carModel}/{carName}/{carPrice}")
    public ServerResponse getCarByPage(@PathVariable("page") Integer page,@PathVariable("carModel") String carModel,@PathVariable("carName") String carName,@PathVariable("carPrice") String carPrice){
        System.out.println("车型："+carModel);
        System.out.println("品牌："+carName);
        System.out.println("价格："+carPrice);
        ServerResponse response = carService.getPageCarModelCarNameCarPrice(page,carModel,carName,carPrice);
        return response;
    }

//    @GetMapping("getCarListByBrand")
//    public ServerResponse getCarListByBrand(@RequestParam("brand") String brand,@RequestParam("page") Integer page){
//        System.out.println("aaaaa");
//        carService.getCarListByBrand(brand);
//        ServerResponse response = carService.scerrenPage(page);
//        System.out.println("查询到的brand数据："+response.getData());
//        return response;
//    }
//    @GetMapping("getCarBrandPage/{page}")
//    public ServerResponse getCarBrandPage(@PathVariable("page") Integer page){
//        ServerResponse response = carService.scerrenPage(page);
//        System.out.println("查询到的数据："+response.getData());
//        return response;
//    }


      //  车辆查询
    @GetMapping("getCarFilter")
    public ServerResponse getCarFilter(@RequestParam("carName") String carName){
        System.out.println("getCarFilter:"+carName);
        ServerResponse response = carService.getCarFilter(carName);
        System.out.println("查询到的数据："+response.getData());
        return response;
    }

}
