package com.suixing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Order;
import com.suixing.mapper.OrderMapper;
import com.suixing.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
    public ServerResponse getById(Integer ordId) {
        Order order = orderMapper.selectById(ordId);
        if (order == null){
            return  ServerResponse.fail("查询失败",null);
        }
        return ServerResponse.success("查询成功",order);
    }


    @Override
    public ServerResponse getOrderAll() {
        List<Order> orderlist = orderMapper.selectList(null);
        return ServerResponse.success("查询成功",orderlist);
    }

    @Override
    public ServerResponse saveOrder(Order order) {
        int rows = orderMapper.insert(order);
        if(rows >0){
            System.out.println("添加成功");
            return ServerResponse.success("添加成功",order);
        }
        else
            System.out.println("添加失败");
            return ServerResponse.fail("添加失败",null);
    }

    @Override
    public Integer updateOrderStatus(Order order) {
        return orderMapper.updateById(order);
    }


}
