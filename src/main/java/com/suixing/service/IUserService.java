package com.suixing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suixing.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
public interface IUserService extends IService<User> {

    //登录
    public User login(Long userTel,String userPsd);
}
