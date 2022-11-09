package com.suixing.service;

import org.springframework.amqp.core.Message;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbitmq.client.Channel;
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
    void saveOrder(Order order, Channel channel, Message message);
    ServerResponse updateOrder(Order order);
    Order getBuOrderNum(Long numId);
    ServerResponse orderStatusSccess(Long ordNumber);




}
