package com.tyut.book.model;

import java.util.Date;

public class BorrowHistory {

    private int id;
    private int borrowerId;
    private int bookId;
    private StatusEnum status;
    private Date createdTime;
    private Date updatedTime;
    private boolean isDeleted;

    public int getId() {
        return id;
    }
    public int getBorrowerId() {
        return borrowerId;
    }
    public int getBookId() {
        return bookId;
    }
    public StatusEnum getStatus() {
        return status;
    }
    public Date getCreatedTime() {
        return createdTime;
    }
    public Date getUpdatedTime() {
        return updatedTime;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
