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
    public void add(User user);
    public void delete(int id);
    public void update(User user);
    public User getUser(int id);
    public List<User> getAllUser();
}
