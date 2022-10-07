package com.suixing.service.impl;

import com.suixing.commons.ServerResponse;
import com.suixing.entity.User;
import com.suixing.mapper.UserMapper;
import com.suixing.service.LoginCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginCustomerServiceImpl implements LoginCustomerService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse Login(Long userTel, String userPsd) {
        User user = new User();

        return null;
    }
}
