package com.suixing.service;

import com.suixing.commons.ServerResponse;
import com.suixing.entity.Car;

public interface IDetailService {
    public ServerResponse getDetails(Integer carId);
    public Car getCarById(Integer carId);
}
