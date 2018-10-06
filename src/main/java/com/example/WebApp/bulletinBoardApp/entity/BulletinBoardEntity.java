package com.example.WebApp.bulletinBoardApp.entity;

import java.util.Date;

/**
 * 一件の掲示板のエンティティ
 *
 * @author rikusekiguchi 2018/07/07
 */
public class BulletinBoardEntity {
    private String  title;
    private String  content;
    private String  userName;
    private Date createDate;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserName() {
        return userName;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
