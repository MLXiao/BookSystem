package com.tyut.book.service.impl;

import com.tyut.book.Constants;
import com.tyut.book.dao.UserDao;
import com.tyut.book.exception.ParameterException;
import com.tyut.book.exception.ServiceException;
import com.tyut.book.model.User;
import com.tyut.book.service.UserService;
import com.tyut.book.util.SessionUtil;
import com.tyut.book.util.StringUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String username, String password, String verifyCode) throws ServiceException, ParameterException {

        ParameterException paramException = new ParameterException();

        if(!verifyCode.equalsIgnoreCase(SessionUtil.getSessionAttribute(Constants.VERIFY_CODE).toString())) {
            paramException.addError("verifyCode", "验证码错误");
            throw paramException;
        }

        User user = userDao.findByName(username);

        if (user == null) {
            throw new ServiceException("用户名或密码错误!");
        }

        if (!StringUtil.toMD5String(password).equals(user.getPassword())) {
            throw new ServiceException("用户名或密码错误!");
        }

        return user;
    }

}
