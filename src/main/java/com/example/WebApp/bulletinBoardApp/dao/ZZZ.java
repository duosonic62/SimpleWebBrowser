package com.example.WebApp.bulletinBoardApp.dao;

import com.example.WebApp.bulletinBoardApp.entity.UserEntity;

public class ZZZ {
    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAO();
            UserEntity userEntity = userDAO.findUser("testUser");
            if(userEntity!=null){
                System.out.println(userEntity.getUserName());
            } else {
                System.out.println("null");
            }

            userDAO.createUser("testUser3");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
