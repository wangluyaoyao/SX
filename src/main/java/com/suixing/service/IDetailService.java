package com.suixing.service;

import com.suixing.commons.ServerResponse;
import com.suixing.entity.Car;
import io.swagger.models.auth.In;

public interface IDetailService {
    public ServerResponse getDetails(Integer carId);
    public Car getCarById(Integer carId);

    //根据carId 分页查询评论 用户信息和评论信息 和回复信息及回复的内容

    public ServerResponse getCommentsByCarId(Integer carId,Integer pageNum);


}
