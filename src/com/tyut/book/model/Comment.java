package com.tyut.book.model;

import java.util.Date;

public class Comment {

    private int id;
    private int userId;
    private int bookId;
    private String content;
    private Date createdTime;
    private Date updatedTime;
    private boolean isdeleted;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    public Date getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    public boolean isIsdeleted() {
        return isdeleted;
    }
    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

}
