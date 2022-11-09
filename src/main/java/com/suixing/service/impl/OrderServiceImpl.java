package com.suixing.service.impl;

import org.springframework.amqp.core.Message;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbitmq.client.Channel;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Order;
import com.suixing.mapper.OrderMapper;
import com.suixing.service.IOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Service
@Component
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getById(Integer ordId) {
        return orderMapper.selectById(ordId);

    }


    @Override
    public ServerResponse getOrderAll() {
        List<Order> orderlist = orderMapper.selectList(null);
        return ServerResponse.success("查询成功",orderlist);
    }


    @Transactional
    @RabbitHandler
    @RabbitListener(queues = "OrderDrawDirectQueue")
    @Override
    public void saveOrder(Order order, Channel channel, Message message) {
        System.out.println("OrderDrawDirectQueue"+order);
       orderMapper.insert(order);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Override
    public ServerResponse updateOrder(Order order) {
        Order order1 = orderMapper.selectById(order.getOrdId());
        order1.setOrdPicIdcard(order.getOrdPicIdcard());
        order1.setOrdPicCard(order.getOrdPicCard());
        int rows = orderMapper.updateById(order1);
        if(rows >0){
            System.out.println("修改成功");
            return ServerResponse.success("修改成功",order1);
        }
        else
            System.out.println("修改失败");
        return ServerResponse.fail("修改失败",null);
    }

    @Override
    public Order getBuOrderNum(Long numId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ord_number",numId);
        return orderMapper.selectOne(queryWrapper);
    }

    @Override
    public ServerResponse orderStatusSccess(Long ordNumber) {
        System.out.println(ordNumber);
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ord_number",ordNumber);
        Order order = orderMapper.selectOne(queryWrapper);
        System.out.println(order);
        order.setOrdSatus("已还车");
        order.setOrdUpdateTime(LocalDateTime.now());
        int row = orderMapper.updateById(order);
        return ServerResponse.success("ok",order);
    }



}
