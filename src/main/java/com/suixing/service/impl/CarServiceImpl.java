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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private CarMapper carMapper;

    List<Car> list = new ArrayList<>();  // 筛选出来的数据
//分页查询
    @Override
    public ServerResponse getPage(int page) {

        Page<Car> curret = new Page<>(page,5);
        Page<Car> pageInfo = carMapper.selectPage(curret,null);
//        List<Car> list = pageInfo.getRecords();
        System.out.println(pageInfo.getRecords());
        if (pageInfo.getRecords() != null)
            return ServerResponse.success("查询成功",pageInfo);
        else
            return ServerResponse.fail("查询失败",null);
    }
    @Override
    public ServerResponse getCarAll() {
        return ServerResponse.success("查询成功",carMapper.selectList(null));
    }

    @Override
    public Car selectId(int carId) {
        return carMapper.selectByCarId(1);
    }

    @Override
    public ServerResponse updateCarImg(Car car) {
        if (carMapper.updateById(car) > 0)
            return ServerResponse.success("插入成功", car);
        return ServerResponse.fail("插入失败",null);
    }

    @Override
    public Car getCarWithFewInfo(int carId) {
        QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
        carQueryWrapper.select("car_id","car_img","car_price");
        carQueryWrapper.eq("car_id",carId);
        Car car = carMapper.selectOne(carQueryWrapper);
        return car;

    }

    @Override
    public ServerResponse getBussiness(int carId) {
        return null;
    }

    //车辆信息查询
    @Override
    public ServerResponse getCarListByBrand(String brand) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_brand",brand);
//        Page<Car> curret = new Page<>(page,5);
//        Page<Car> pageInfo = carMapper.selectPage(curret,queryWrapper);

        List<Car> carList =  carMapper.selectList(queryWrapper);
        System.out.println("getCarListByBrand:"+carList);
        list.addAll(carList);

        if (carList!=null)
            return ServerResponse.success("查询成功",carList);
        return ServerResponse.fail("查询失败",null);
    }
    @Override
    public ServerResponse scerrenPage(int page) {
        int pageNo = page; //当前页数
        int pageSize = 5; //一页多少条
        int total = list.size();  //总数
        int pageSum = total % pageSize == 0 ? total/ pageSize : total/ pageSize + 1;  //总页数
        List<Car> subList = list.stream().skip((pageNo - 1)* pageSize).limit(pageSize).collect(Collectors.toList());
        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("pageList",subList);
        pageMap.put("pageNo",pageNo);
        pageMap.put("pageSize",pageSize);
        pageMap.put("pageSum",pageSum);
        pageMap.put("total",total);

        return ServerResponse.success("查询成功",pageMap);
    }

}
