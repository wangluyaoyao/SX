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

import java.util.*;
import java.util.function.Consumer;
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
    int connt = 0;
//分页查询
    @Override
    public ServerResponse getPage(int page) {
        System.out.println("要查询的页数"+page);
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
    public ServerResponse getPageCarModelCarNameCarPrice(int page, String carModel, String carBrand, String carPrice) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        if (!carModel.equals("no")){
            queryWrapper.eq("car_model",carModel);
        }
        if (!carBrand.equals("no")){
            String [] brandArray  = carBrand.split(" ");
            System.out.println(Arrays.toString(brandArray));

            queryWrapper.and(wrapper -> {
                    for (int i = 0; i < brandArray.length; i++) {
                       wrapper.or().eq("car_brand",brandArray[i].trim());
                       System.out.println(brandArray[i].trim());
                 }
            });
        }


        if (!carPrice.equals("no")){

            System.out.println(carPrice.length());
            if (carPrice.length()==5){
                int max = Integer.parseInt(carPrice.substring(carPrice.length()-3));
                queryWrapper.lt("car_price",max);
            }else if (carPrice.length() == 3){
                int min = Integer.parseInt(carPrice.substring(0,3));
                queryWrapper.gt("car_price",min);
            }else {
                int min = Integer.parseInt(carPrice.substring(0,3));
                int max = Integer.parseInt(carPrice.substring(carPrice.length()-3));
                queryWrapper.between("car_price",min,max);
                System.out.println("min:"+min);
                System.out.println("max"+max);
            }
        }




        Page<Car> curret = new Page<>(page,5);
        Page<Car> pageInfo = carMapper.selectPage(curret,queryWrapper);
//        List<Car> list = pageInfo.getRecords();
        System.out.println(pageInfo.getRecords());
        if (pageInfo.getRecords() != null)
            return ServerResponse.success("查询成功",pageInfo);
        else
            return ServerResponse.fail("查询失败",null);
    }


    //方法重载
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


    @Override
    public ServerResponse getCarFilter(String carName) {
        if (carName == null)
           return ServerResponse.success("查询成功",carMapper.selectList(null));
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("car_name",carName);
        queryWrapper.select("car_name","car_price","car_model","car_disp","car_seat","car_img");

        List<Car> carList =   carMapper.selectList(queryWrapper);
        if (carList!=null)
            return ServerResponse.success("查询成功",carList);
        return ServerResponse.fail("查询失败",null);
    }

    @Override
    public ServerResponse getById(int carId) {
        return null;
    }

}
