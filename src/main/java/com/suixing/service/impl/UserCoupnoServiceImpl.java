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
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Service
//@Component  //将对象层(当前类)的对象 注入到IOC容器中
public class UserCoupnoServiceImpl extends ServiceImpl<UserCoupnoMapper, UserCoupno> implements IUserCoupnoService {
    @Autowired
    private UserCoupnoMapper userCoupno;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private UserCoupnoMapper userCoupnoMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    private DefaultRedisScript script;

    @PostConstruct
    public void init(){

        script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("skillsecond.lua")));
    }

    @Override
    public ServerResponse getById(int userId) {
        UserCoupno userCoupno =  userCoupnoMapper.selectById(userId);
        if (userCoupno !=null)
            return ServerResponse.success("领取成功",userCoupno);
        return ServerResponse.fail("领取失败",null);
    }

    //抢券高并发处理
    @Override
    public ServerResponse userRecCou(Integer userId, Integer couId) {
        System.out.println("用户id:"+userId+"优惠卷id:"+couId);
//      查询优惠卷的优惠周期
        String result =   setRedis(userId,couId);
//        if (coupon == null)
//            ServerResponse.success("优惠券被抢光了",null);
        QueryWrapper<Coupon> cidition = new QueryWrapper<>();
        cidition.eq("cou_id",couId);
//        cidition.select("cou_cycle","cou_amount");
        Coupon coupon =  couponMapper.selectOne(cidition); //查询对应的优惠卷信息
        UserCoupno usercoupon = caeatUserCou(coupon,userId); //用户领取的优惠券的信息生成
        if (userCoupnoMapper.insert(usercoupon)>0){
            System.out.println("用户优惠券信息添加成功");
        }
//        String newUserCou =  getUserPoupno(userId,couId,usercoupon,coupon); //向USER表添加数据
        return ServerResponse.success(result,usercoupon);
    }


    //redis处理高并发
    public String setRedis(int userId,int couId){
        // 先查 redis
        Coupon coupon = null;
        String key  =  "skill_pro_"+couId;
        String uuid = UUID.randomUUID().toString().replace("-","");
        boolean isLock = redisTemplate.opsForValue().setIfAbsent(key,uuid,100, TimeUnit.SECONDS);
        if (isLock){//某人获得了锁
            System.out.println(Thread.currentThread().getName()+"抢到了该优惠券");
            //查询优惠券信息
            coupon = (Coupon)redisTemplate.opsForValue().get("coupon_"+couId);
            System.out.println("在redis中查询到优惠券的数据"+coupon);
            if (coupon == null)
                return "优惠券被抢光了";
            if (coupon.getCouAmount() == 0) {
                return "优惠券被抢光了";
            }else {
                coupon.setCouAmount(coupon.getCouAmount()-1);
                redisTemplate.opsForValue().set("coupon_"+couId,coupon,12,TimeUnit.HOURS);
                //执行lua脚本 ，删除锁，保证原子性
                Long execute = (Long) redisTemplate.execute(script, Arrays.asList(key),uuid);

            }
            updateUserCoupno(coupon);
            return "领取成功";
        }else { //加锁失败，过0.1秒再获得锁
            try {
                Thread.sleep(100);
                coupon = (Coupon) redisTemplate.opsForValue().get("coupon_"+couId);
                if (coupon != null && coupon.getCouAmount()>0){
                    userRecCou(userId,couId);
                    return null;
                }else {
                    return null;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //数据库信息修改
    public void  updateUserCoupno(Coupon coupon){
        couponMapper.updateById(coupon);
        System.out.println("数据库数据修改成功");
    }
    //数据库信息修改
    public String getUserPoupno(int userId,int couId,UserCoupno usercoupon,Coupon coupon){
        couponMapper.updateById(coupon);
        QueryWrapper<UserCoupno> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("cou_id",couId);
        queryWrapper.eq("user_id",userId);
        queryWrapper.select("user_cou_id");
//        UserCoupno coupno = userCoupnoMapper.selectOne(queryWrapper);
//        if (coupno!= null)
//            return "你已经领取过该优惠券了";
        if (userCoupnoMapper.insert(usercoupon)>0) {
            //更新数据库优惠见数量
            Coupon newAmountCoupno =   new Coupon();
            newAmountCoupno.setCouAmount(coupon.getCouAmount()-1);
            if (coupon.getCouAmount()==0)
                return "该优惠卷已经被领完了";
            couponMapper.updateById(newAmountCoupno);
            return "领取成功";
        }
        return "数据修改成功";
    }
    //UserCoupno信息生成
    public UserCoupno caeatUserCou(Coupon coupon,int userId){
        //生成优惠券信息
        UserCoupno usercoupon = new UserCoupno();
        usercoupon.setUserCouNum(NumGeneration.creatCoupnoNum());
        usercoupon.setCouId(coupon.getCouId());
        usercoupon.setUserId(userId);
        Date date = new Date();
//        日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        usercoupon.setUserCouTime(calendar.getTime());
        usercoupon.setUserCouStart(calendar.getTime());
        int day = Integer.parseInt(coupon.getCouCycle()); //获得优惠卷优惠周期天数
        calendar.add(Calendar.DATE,day);//增加指定天数
        usercoupon.setUserCouEnd(calendar.getTime()); //结束时间
        usercoupon.setUserCouState("0"); // 0:未使用
        return usercoupon;
    }

}
