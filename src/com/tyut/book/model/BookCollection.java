package com.tyut.book.model;

import java.util.Date;

public class BookCollection {

    private int userId;
    private int bookId;
    private String bookName;
    private byte[] cover;
    private Date createdTime;
    private boolean isDeleted;

    public int getUserId() {
        return userId;
    }
    public int getBookId() {
        return bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public Date getCreatedTime() {
        return createdTime;
    }
    public boolean getIsDeleted() {
        return isDeleted;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    public void setIdDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public byte[] getCover() {
        return cover;
    }
    public void setCover(byte[] cover) {
        this.cover = cover;
    }

}
