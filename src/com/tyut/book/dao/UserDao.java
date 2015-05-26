package com.tyut.book.dao;

import com.tyut.book.model.BookCollection;
import com.tyut.book.model.StatusEnum;
import com.tyut.book.model.User;

public interface UserDao {

    public User findByName(String username);

    public int collectBook(int userId, int bookId);

    public BookCollection getBookCollection(int userId, int bookId);

    public int createBorrowHistory(int userId, int bookId);

    public int updateHistoryStatus(int historyId, StatusEnum status);

    public int getHistoryId(int userId, int bookId);

}
