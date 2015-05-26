package com.tyut.book.service;

import java.util.List;

import com.tyut.book.exception.ParameterException;
import com.tyut.book.exception.ServiceException;
import com.tyut.book.model.Message;
import com.tyut.book.model.User;

public interface UserService {

    public User login(String username, String password, String verifyCode) throws ServiceException, ParameterException;

    public int collectBook(int userId, int bookId);

    public boolean isBookCollected(int userId, int bookId);

    public int requeirBorrowBook(int userId, int bookId);

    public int getMessageCount(int userId);

    public List<Message> findMessages(int userId, String status);

    public boolean dealMessage(int messageId, boolean result);

}
