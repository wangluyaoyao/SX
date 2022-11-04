package com.suixing.controller;


import com.aliyuncs.utils.StringUtils;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.LoginCustomer;
import com.suixing.entity.User;
import com.suixing.service.IUserService;
import com.suixing.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */

//@Slf4j
@RestController
@RequestMapping("/customer")
public class UserController {

//    植入对象


    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("login")

    public ServerResponse login(User user,HttpServletRequest request,HttpServletResponse response){
        ServerResponse result = userService.login(user);
        System.out.println("controller login response:"+result);
        if (result.getResultcode() == 200){
            String token = (String) result.getData();
            System.out.println("customer controller 登陆成功");
            System.out.println(token);
        }
        return result;
    }
    //    页面验证显示用户名
    @PostMapping("userVerification/{token}")
    public ServerResponse userVerification(@PathVariable("token") String token){
    //    System.out.println("token:"+token);
        LoginCustomer loginCustomer = null;
        String loginUserName = null;
        if (token!=null){
            loginCustomer = TokenUtil.parseToken(token);
            loginUserName =   loginCustomer.getUserName();
        }
       // System.out.println("loginUserName:"+loginUserName);
        return ServerResponse.success("查询成功",loginCustomer);
    }
    @PostMapping("regist")
    public ServerResponse regist(User user,HttpServletRequest request,HttpServletResponse response){

        return userService.regist(user);
    }


    @GetMapping("/send")
    public ServerResponse sendCode(@RequestParam("phone")String phone){

        System.out.println("controller:"+phone);
        // 根据手机号从redis中拿验证码
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return ServerResponse.success("发送成功",code);
        }
        // 如果redis 中根据手机号拿不到验证码，则生成6位随机验证码
        code = String.valueOf(UUID.randomUUID().toString().hashCode()).replaceAll("-","").substring(0,6);

        System.out.println("code:"+code);
        // 验证码存入codeMap
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);
        // 调用aliyunSendSmsService发送短信
        Boolean bool = userService.sendMessage(phone, code, codeMap);
        System.out.println(code);
        if (bool) {
            // 如果发送成功，则将生成的4位随机验证码存入redis缓存,5分钟后过期
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return ServerResponse.success("发送成功",code);
        } else {
            return ServerResponse.fail("发送失败 ",null);
        }
    }

}




