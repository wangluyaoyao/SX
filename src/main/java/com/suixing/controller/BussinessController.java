package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.service.IBussinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class BussinessController {
    @Autowired
    private IBussinessService bussinessService;


    @GetMapping("bussiness")
    @ResponseBody
    public ServerResponse selectAll(){
        ServerResponse result = bussinessService.selectAll();
        return result;

    }

}
