package com.suixing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Order;
import com.suixing.mapper.OrderMapper;
import com.suixing.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ServerResponse selectOrderAll() {
        List<Order> orderList = orderMapper.selectList(null);
        if (orderList != null){
            return ServerResponse.success("查询成功",orderList);
        }
        return ServerResponse.fail("查询失败",null);
    }

    @Override
    public ServerResponse selectbyOrdSatus(String ordSatus) {
        return null;
    }


    @Override
    public ServerResponse selectByOrderNum(Long ordNumber) {
        Order order = orderMapper.selectById(ordNumber);
        if (order == null){
            return  ServerResponse.fail("查询失败",null);
        }
            return ServerResponse.success("查询成功",order);
    }

    @Override
    public ServerResponse saveOrder(Order order) {
        int rows = orderMapper.insert(order);
        if(rows >0)
            return ServerResponse.success("添加成功",order);
        else
            return ServerResponse.fail("添加失败",null);
    }


}
