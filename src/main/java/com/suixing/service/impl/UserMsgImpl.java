package com.suixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Car;
import com.suixing.entity.UserMsg;
import com.suixing.mapper.CarMapper;
import com.suixing.mapper.UserMsgMapper;
import com.suixing.service.ICarService;
import com.suixing.service.IUserMsgService;
import org.elasticsearch.client.license.LicensesStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMsgImpl extends ServiceImpl<UserMsgMapper, UserMsg> implements IUserMsgService {
    @Autowired
    private UserMsgMapper userMsgMapper;
    @Override
    public ServerResponse getUserMsg(int userId) {
        QueryWrapper<UserMsg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.select().orderByDesc("user_msg_time");
        List<UserMsg> userMsgList =  userMsgMapper.selectList(queryWrapper);
        if (userMsgList!=null)
            return ServerResponse.success("查询成功",userMsgList);
        return ServerResponse.fail("查询失败",null);
    }

    @Override
    public ServerResponse msgUpdate(int userId, int msgId) {
        QueryWrapper<UserMsg> selectOne = new QueryWrapper<>();
        selectOne.eq("user_id",userId);
        selectOne.eq("user_msg_id",msgId);
        UserMsg userMsg =  userMsgMapper.selectOne(selectOne);

        userMsg.setUserMsgStatus("1");
        QueryWrapper<UserMsg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("user_msg_id",msgId);
        int result =  userMsgMapper.update(userMsg,queryWrapper);

       if (result>0) {
           QueryWrapper<UserMsg> queryWrapperOld = new QueryWrapper<>();
           queryWrapperOld.eq("user_id", userId);
           queryWrapper.select().orderByDesc("user_msg_time");
           List<UserMsg> userMsgList = userMsgMapper.selectList(queryWrapperOld);
           return ServerResponse.success("修改成功", userMsgList);
       }
       return ServerResponse.fail("修改失败",null);
    }
}
