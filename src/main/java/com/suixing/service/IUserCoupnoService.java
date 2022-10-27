package com.suixing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.UserCoupno;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
public interface IUserCoupnoService extends IService<UserCoupno> {
    public ServerResponse userRecCou(Integer userId,Integer couId);

    //查询用户优惠券
    UserCoupno getCoupnoOwn(int userCouId);

}
