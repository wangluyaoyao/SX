package com.suixing.service.impl;

import com.suixing.commons.ServerResponse;
import com.suixing.entity.User;
import com.suixing.mapper.UserMapper;
import com.suixing.service.IUserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCenterServiceImpl implements IUserCenterService {
    @Autowired
    private UserMapper userMapper;
    //用户通过主键查询用户
    @Override
    public ServerResponse getUserById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null){
            return ServerResponse.success("查询成功",user);
        }
        return ServerResponse.fail("查询失败！",null);
    }
    //用户通过主键查询用户
    @Override
    public User getUserUpdateById(Integer userId) {
        User user = userMapper.selectById(userId);
        return user;
    }

    @Override
    public ServerResponse updateUser(User user) {
        int row = userMapper.updateById(user);
        if (row == 1){
            return ServerResponse.success("修改成功",null);
        }
        return ServerResponse.fail("修改失败",null);
    }
}
