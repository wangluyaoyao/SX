package com.suixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.NumGeneration;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Coupon;
import com.suixing.entity.UserCoupno;
import com.suixing.mapper.CouponMapper;
import com.suixing.mapper.UserCoupnoMapper;
import com.suixing.service.IUserCoupnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Service
@Component  //将对象层(当前类)的对象 注入到IOC容器中
public class UserCoupnoServiceImpl extends ServiceImpl<UserCoupnoMapper, UserCoupno> implements IUserCoupnoService {
    @Autowired
    private UserCoupnoMapper userCoupno;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private UserCoupnoMapper userCoupnoMapper;


    @Override
    public ServerResponse userRecCou(Integer userId, Integer couId) {
        System.out.println("用户id:"+userId);
        System.out.println("优惠卷id:"+couId);
        //生成优惠券信息
        UserCoupno usercoupon = new UserCoupno();
        usercoupon.setUserCouNum(NumGeneration.creatCoupnoNum());
        usercoupon.setCouId(couId);
        usercoupon.setUserId(userId);
        Date date = new Date();
//        日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        usercoupon.setUserCouTime(calendar.getTime());
        usercoupon.setUserCouStart(calendar.getTime());
//      查询优惠卷的优惠周期
        QueryWrapper<Coupon> couType = new QueryWrapper<>();
        couType.eq("cou_id",couId);
        couType.select("cou_cycle");
        Coupon coupon =  couponMapper.selectOne(couType); //查询对应的优惠卷信息
        System.out.println(coupon);
        int day = Integer.parseInt(coupon.getCouCycle()); //获得优惠卷优惠周期天数

        calendar.add(Calendar.DATE,day);//增加指定天数
        usercoupon.setUserCouEnd(calendar.getTime()); //结束时间
        usercoupon.setUserCouState("0"); // 0:未使用
        return ServerResponse.success(getUserPoupno(userId,couId,usercoupon),usercoupon);
    }


    @Override
    public ServerResponse getById(int userId) {
        UserCoupno userCoupno =  userCoupnoMapper.selectById(userId);
        if (userCoupno !=null)
            return ServerResponse.success("领取成功",userCoupno);
        return ServerResponse.fail("领取失败",null);

    }

    public String getUserPoupno(int userId,int couId,UserCoupno usercoupon){
        QueryWrapper<UserCoupno> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("cou_id",couId);
        queryWrapper.eq("user_id",userId);
        queryWrapper.select("user_cou_id");
        UserCoupno coupno = userCoupnoMapper.selectOne(queryWrapper);
        if (coupno!= null)
            return "你已经领取过该优惠券了";
        if (userCoupnoMapper.insert(usercoupon)>0)
            return "领取成功";
        return "领取失败";
    }

}
