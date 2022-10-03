package com.suixing.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
    Car selectId(int carId);
}
