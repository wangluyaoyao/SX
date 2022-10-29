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
        UserCoupno coupon = new UserCoupno();
        coupon.setUserCouId(NumGeneration.creatCoupnoNum());
        coupon.setCouId(couId);
        coupon.setUserId(userId);
        Date date = new Date();
//        日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        coupon.setUserCouTime(calendar.getTime());
        coupon.setUserCouStart(calendar.getTime());
//      查询优惠卷的优惠周期
        QueryWrapper<Coupon> couType = new QueryWrapper<>();
        couType.eq("cou_id",couId);
        couType.select("cou_cucle");
        int day = Integer.parseInt(couponMapper.selectOne(couType).getCouCycle()); //获得优惠卷优惠周期天数
        calendar.add(Calendar.DATE,day);//增加指定天数
        coupon.setUserCouEnd(calendar.getTime()); //结束时间
        coupon.setUserCouState("0"); // 0:未使用
       if (userCoupno.insert(coupon)>0)
           return ServerResponse.success("领取成功",coupon);
        return ServerResponse.fail("领取失败",null);
    }

    @Override
    public UserCoupno getCoupnoOwn(int userCouId) {
        //获取优惠券部分信息
        QueryWrapper<UserCoupno> userCoupnoQueryWrapper = new QueryWrapper<>();
        userCoupnoQueryWrapper.select("user_cou_id","cou_price","cou_explain","cou_end");
        userCoupnoQueryWrapper.eq("user_cou_id",userCouId);
        UserCoupno userCoupno = userCoupnoMapper.selectOne(userCoupnoQueryWrapper);
        return userCoupno;
    }

}
