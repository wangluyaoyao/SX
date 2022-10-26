package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Controller
@RequestMapping("/Car")
public class CarController {
    @Autowired
    private ICarService carService;
    @GetMapping("getCarById/{page}")
    @ResponseBody
    public ServerResponse getCarById(@PathVariable("page") Integer page){
        ServerResponse response = carService.getPage(page);
        System.out.println("查询到的数据："+response.getData());
        return response;
    }
}
