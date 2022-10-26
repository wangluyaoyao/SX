package com.suixing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Coupon;
import com.suixing.mapper.CouponMapper;
import com.suixing.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {
    @Autowired
    private CouponMapper couponMapper;
    @Override
    public ServerResponse getCouponAll() {
        List<Coupon> allList = couponMapper.selectList(null);
        if (allList != null) {
            List<Coupon> couponList = new ArrayList<>();
            for (Coupon coupon : allList) {
                if (coupon.getCouAmount()>0){
                    System.out.println(coupon);
                    couponList.add(coupon);
                }
            }
            return ServerResponse.success("查询成功",couponList);
        }

        return ServerResponse.fail("查询失败",null);

    }
}
