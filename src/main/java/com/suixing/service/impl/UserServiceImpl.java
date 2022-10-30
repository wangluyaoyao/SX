package com.suixing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.LoginCustomer;
import com.suixing.entity.User;
import com.suixing.mapper.UserMapper;
import com.suixing.service.IUserService;
import com.suixing.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Slf4j
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

    @Override
    public ServerResponse regist(User user) {
       int rows = userMapper.insert(user);
       if (rows > 0)
           return ServerResponse.success("注册成功",user);
       else
           return ServerResponse.fail("注册失败",null);
    }



//    private static final String accessKey = "LTAI5tAuf9NzKcXVeVdf5m7K"; // 密钥Id
//    private static final String secret = "FkwGJUt85vXGyXCiVTPV82IUTJXYnW"; // 密钥
//    private static final String PhoneNumbers = "15995109573"; // 手机号
//    private static final String SignName = "阿里云短信测试"; // 短信签名测试
//    private static final String TemplateCode = "SMS_256040479"; // 短信模板
//    private static final String TemplateParam = "{\"code\":\"123456\"}";
//    @Override
//    public Boolean sendPhoneCode(Map<String, Object> map, String phone) {
//        if (Strings.isEmpty(phone)) return false; // 手机号不为空
//        //                                                                   密钥id    密钥
//        DefaultProfile profile = DefaultProfile.getProfile("default", accessKey, secret);
//        IAcsClient client = new DefaultAcsClient(profile);
//        // 创建请求
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST); // post方法
//        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setSysVersion("2017-05-25"); // API版本号
//        request.setSysAction("SendSms");
//        request.putQueryParameter("PhoneNumbers", phone); // 手机号
//        request.putQueryParameter("SignName", SignName); // 签名名称
//        request.putQueryParameter("TemplateCode", TemplateCode); // 短信模板
//        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map)); // 验证码转成json数据
//        try {
//            // 得到返回值
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//            System.out.println(response.getHttpResponse());
//            return response.getHttpResponse().isSuccess();
//        } catch (ClientException | com.aliyuncs.exceptions.ClientException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
