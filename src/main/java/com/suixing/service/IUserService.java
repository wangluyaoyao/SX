package com.suixing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */

//@Service
public interface IUserService extends IService<User> {


    //登录
//    public User selectUserById(int userId);

    public ServerResponse  login(User user);
}
