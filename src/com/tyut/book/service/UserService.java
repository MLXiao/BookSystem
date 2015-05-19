package com.tyut.book.service;

import com.tyut.book.model.User;

public interface UserService {

    public User login(String username, String password, String verifyCode);
}
