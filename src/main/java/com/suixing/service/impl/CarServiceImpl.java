package com.suixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Car;
import com.suixing.entity.UserCoupno;
import com.suixing.mapper.CarMapper;
import com.suixing.service.ICarService;
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
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {
    @Autowired
    private CarMapper CarMapper;
//分页查询
    @Override
    public ServerResponse getPage(int page) {

        Page<Car> curret = new Page<>(page,5);
        Page<Car> pageInfo = CarMapper.selectPage(curret,null);
//        List<Car> list = pageInfo.getRecords();
        System.out.println(pageInfo.getRecords());
        if (pageInfo.getRecords() != null)
            return ServerResponse.success("查询成功",pageInfo);
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

    @Override
    public ServerResponse updateCarImg(Car car) {
        if (CarMapper.updateById(car) > 0)
            return ServerResponse.success("插入成功", car);
        return ServerResponse.fail("插入失败",null);
    }

    //车辆信息查询
    @Override
    public Car getCarWithFewInfo(int carId) {
        QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
        carQueryWrapper.select("car_id","car_img","car_price");
        carQueryWrapper.eq("car_id",carId);
        Car car = CarMapper.selectOne(carQueryWrapper);
        return car;

    }

    //网点地址
    @Override
    public ServerResponse getBussiness(int carId) {
        return CarMapper.getBussiness(carId);
    }
}
