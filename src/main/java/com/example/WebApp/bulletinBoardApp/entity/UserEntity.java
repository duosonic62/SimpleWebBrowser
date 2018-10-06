package com.example.WebApp.bulletinBoardApp.entity;


/**
 * 一件のユーザのエンティティ
 *
 * @author rikusekiguchi 2018/07/07
 */
public class UserEntity {
    private Integer userId;
    private String  userName;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
