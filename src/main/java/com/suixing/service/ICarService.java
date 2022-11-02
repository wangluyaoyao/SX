package com.suixing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Car;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
public interface ICarService extends IService<Car> {
    ServerResponse getPage(int page);
    ServerResponse getPageCarModelCarNameCarPrice(int page,String carModel,String carBrand,String carPrice);
    ServerResponse getCarAll();
    Car selectId(int carId);
    ServerResponse updateCarImg(Car car);
    ServerResponse getCarListByBrand(String brand);
    public Car getCarWithFewInfo(int carId);
    public ServerResponse getBussiness(int carId);
    ServerResponse scerrenPage(int page);
    ServerResponse getCarFilter(String carName);
    ServerResponse getById(int carId);
}
