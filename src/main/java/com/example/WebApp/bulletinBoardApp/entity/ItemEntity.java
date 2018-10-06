package com.example.WebApp.bulletinBoardApp.entity;

import java.sql.Timestamp;

/**
 * 一件の記事のエンティティ
 *
 * @author rikusekiguchi 2018/07/07
 */
public class ItemEntity {
    private Integer itemId;
    private String  title;
    private String  content;
    private Integer userId;
    private Timestamp createTime;

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getUserId() {
        return userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
}
