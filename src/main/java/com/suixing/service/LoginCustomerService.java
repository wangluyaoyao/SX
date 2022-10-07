package com.suixing.service;

import com.suixing.commons.ServerResponse;

public interface LoginCustomerService {

    //用户登录
    public ServerResponse Login(Long userTel,String userPsd);

    //解密
//    public String getPrivateKey();

    //登录

}
