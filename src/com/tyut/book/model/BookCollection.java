package com.tyut.book.model;

import java.util.Date;

public class BookCollection {

    private int userId;
    private int bookId;
    private Date created_time;
    private boolean is_deleted;

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
    public Date getCreated_time() {
        return created_time;
    }
    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }
    public boolean isIs_deleted() {
        return is_deleted;
    }
    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

}
