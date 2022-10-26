package com.suixing.entity;

import io.swagger.models.auth.In;

public class LoginCustomer {
    private Integer userId;
    private String userName;

    public LoginCustomer(){
    }

    public LoginCustomer(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LoginCustomer{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
