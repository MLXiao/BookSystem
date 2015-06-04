package com.tyut.book.service;

import java.util.List;

import com.tyut.book.exception.ParameterException;
import com.tyut.book.exception.ServiceException;
import com.tyut.book.model.BookCollection;
import com.tyut.book.model.BorrowHistory;
import com.tyut.book.model.Message;
import com.tyut.book.model.Pagination;
import com.tyut.book.model.User;

public interface UserService {

    public User login(String username, String password, String verifyCode) throws ServiceException, ParameterException;

    public int collectBook(int userId, int bookId);

    public boolean isBookCollected(int userId, int bookId);

    public int requeirBorrowBook(int userId, int bookId);

    public int getMessageCount(int userId);

    public List<Message> findMessages(int userId, String status);

    public boolean dealMessage(int messageId, boolean result);

    public int getCollectionCount(int userId);

    public List<BookCollection> findCollections(int userId,
            Pagination pagination);

    public int deleteCollection(int bookId);

    public int getHistoryCount(int userId, String status);

    public List<BorrowHistory> findBorrowHistory(int userId,
            Pagination pagination, String status);

    public int returnBook(int userId, int bookId);

    public User register(User user);

    public User getUserById(int id);

    public int update(User user);


}
