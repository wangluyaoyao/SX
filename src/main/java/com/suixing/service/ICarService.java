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
    ServerResponse getCarAll();
    Car selectId(int carId);
    ServerResponse updateCarImg(Car car);
    ServerResponse getCarListByBrand(String brand);
    ServerResponse scerrenPage(int page);

    ServerResponse getById(int carId);
}
