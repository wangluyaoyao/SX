package com.suixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Coupon;
import com.suixing.entity.User;
import com.suixing.entity.UserCoupno;
import com.suixing.mapper.CouponMapper;
import com.suixing.mapper.UserCoupnoMapper;
import com.suixing.mapper.UserMapper;
import com.suixing.service.IUserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserCenterServiceImpl implements IUserCenterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserCoupnoMapper userCoupnoMapper;
    @Autowired
    private CouponMapper couponMapper;
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

    @Override
    public ServerResponse getUserCoupon(Integer userId) {
        QueryWrapper<UserCoupno> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<UserCoupno> userCoupnoList = userCoupnoMapper.selectList(wrapper);
        //userCoupnoList.forEach(System.out::println);
        List<Map<String,Object>> listCoupon = new ArrayList<>();
        for (UserCoupno userCoupno: userCoupnoList){
            Map<String,Object> mapCoupon = new HashMap<>();
            Coupon coupon = couponMapper.selectById(userCoupno.getCouId());
            //日期格式转换
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(userCoupno.getUserCouEnd());


            mapCoupon.put("couponUserCouNum",userCoupno.getUserCouNum());
            mapCoupon.put("couponExplain",coupon.getCouExplain());
            mapCoupon.put("couponTimeEnd",time);
            mapCoupon.put("couponState",userCoupno.getUserCouState());
            listCoupon.add(mapCoupon);
        }

        return ServerResponse.success("ok",listCoupon);
    }
}
