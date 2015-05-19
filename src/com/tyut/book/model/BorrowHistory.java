package com.tyut.book.model;

import java.util.Date;

public class BorrowHistory {

    private int id;
    private int borrowerId;
    private int bookId;
    private boolean isReturned;
    private Date borrowedTime;
    private Date returnedTime;
    private boolean isDeleted;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBorrowerId() {
        return borrowerId;
    }
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public boolean isReturned() {
        return isReturned;
    }
    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }
    public Date getBorrowedTime() {
        return borrowedTime;
    }
    public void setBorrowedTime(Date borrowedTime) {
        this.borrowedTime = borrowedTime;
    }
    public Date getReturnedTime() {
        return returnedTime;
    }
    public void setReturnedTime(Date returnedTime) {
        this.returnedTime = returnedTime;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
