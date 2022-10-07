package com.suixing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Car;
import com.suixing.mapper.CarMapper;
import com.suixing.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {
    @Autowired
    private CarMapper CarMapper;
//分页查询
    @Override
    public ServerResponse getPage(int page) {
        Page<Car> curret = new Page<>(1,3);
        Page<Car> pageInfo = CarMapper.selectPage(curret,null);
        List<Car> list = pageInfo.getRecords();
        if (list != null)
            return ServerResponse.success("查询成功",list);
        else
            return ServerResponse.fail("查询失败",null);
    }
    @Override
    public ServerResponse getCarAll() {
        return ServerResponse.success("查询成功",CarMapper.selectList(null));
    }

    @Override
    public Car selectId(int carId) {
        return CarMapper.selectByCarId(1);
    }
}
