package com.tyut.book.model;
import java.util.Date;

public class User{

    private int id;
    private String username;
    private String password;
    private String nickname;
    private RoleEnum role;
    private String phomeNum;
    private String email;
    private int balance;
    private GenderEnum gender;
    private byte[] avotor;
    private boolean isForbidden;
    private Date createdTime;
    private Date updatedTime;
    private boolean isDeleted;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public RoleEnum getRole() {
        return role;
    }
    public void setRole(RoleEnum role) {
        this.role = role;
    }
    public String getPhomeNum() {
        return phomeNum;
    }
    public void setPhomeNum(String phomeNum) {
        this.phomeNum = phomeNum;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public GenderEnum getGender() {
        return gender;
    }
    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    public byte[] getAvotor() {
        return avotor;
    }
    public void setAvotor(byte[] avotor) {
        this.avotor = avotor;
    }
    public boolean isForbidden() {
        return isForbidden;
    }
    public void setForbidden(boolean isForbidden) {
        this.isForbidden = isForbidden;
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
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
