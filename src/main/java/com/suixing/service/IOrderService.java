package com.suixing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Order;

import javax.xml.transform.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
public interface IOrderService  {

    Order getById(Integer ordId);
    ServerResponse getOrderAll();
    ServerResponse saveOrder(Order order);
    ServerResponse updateOrder(Order order);
    Order jixuzhifu(Long ordNumber);
    ServerResponse orderStatus(Long ordNumber);

    ServerResponse orderStatusSccess(Long ordNumber);




}
