package com.suixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.LoginCustomer;
import com.suixing.entity.User;
import com.suixing.mapper.UserMapper;
import com.suixing.service.IUserService;
import com.suixing.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public ServerResponse login(User user) {
        System.out.println(user);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_tel",user.getUserTel());
        wrapper.eq("user_psd",user.getUserPsd());

        User loginUser = userMapper.selectOne(wrapper);
        System.out.println("查询到的登录账户："+loginUser);

        if (loginUser != null){
            LoginCustomer loginCustomer = new LoginCustomer(loginUser.getUserId(),loginUser.getUserName());
            String token = TokenUtil.getToken(loginCustomer);
            System.out.println("service token:"+token);
            return ServerResponse.success("登陆成功",token);
        }else {
            return ServerResponse.fail("登陆失败",null);
        }

    }
}
