package com.tyut.book.model;

import java.util.Date;

public class Message {

    private int id;
    private MessageTypeEnum type;
    private int bookId;
    private String bookName;
    private int senderId;
    private String senderName;
    private int receiverId;
    private String receiverName;
    private boolean isHandled;
    private String comment;
    private Date createdTime;
    private Date updatedTime;
    private boolean result;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public MessageTypeEnum getType() {
        return type;
    }
    public void setType(MessageTypeEnum type) {
        this.type = type;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getSenderId() {
        return senderId;
    }
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
    public int getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
    public boolean getHandled() {
        return isHandled;
    }
    public void setHandled(boolean isHandled) {
        this.isHandled = isHandled;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
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
    public boolean getResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getSenderName() {
        return senderName;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

}
