package com.tyut.book.dao;

import com.tyut.book.model.User;

public interface UserDao {

    public User findByName(String username);

}
