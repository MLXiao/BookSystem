package com.tyut.book.service;

import com.tyut.book.exception.ParameterException;
import com.tyut.book.exception.ServiceException;
import com.tyut.book.model.User;

public interface UserService {

    public User login(String username, String password, String verifyCode) throws ServiceException, ParameterException;
}
