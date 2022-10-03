package com.suixing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.entity.Car;
import com.suixing.mapper.CarMapper;
import com.suixing.service.ICarService;
import org.springframework.stereotype.Service;

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

}
