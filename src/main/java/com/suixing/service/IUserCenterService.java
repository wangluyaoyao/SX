package com.suixing.service;

import com.suixing.commons.ServerResponse;
import com.suixing.entity.User;

public interface IUserCenterService {
    ServerResponse getUserById(Integer userId);
    User getUserUpdateById(Integer userId);
    ServerResponse updateUser(User user);

    ServerResponse getUserCoupon(Integer userId);
}
