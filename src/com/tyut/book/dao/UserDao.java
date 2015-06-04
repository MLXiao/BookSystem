package com.tyut.book.dao;

import java.util.List;

import com.tyut.book.model.BookCollection;
import com.tyut.book.model.BorrowHistory;
import com.tyut.book.model.Pagination;
import com.tyut.book.model.StatusEnum;
import com.tyut.book.model.User;

public interface UserDao {

    public User findByName(String username);

    public int collectBook(int userId, int bookId);

    public BookCollection getBookCollection(int userId, int bookId);

    public int createBorrowHistory(int userId, int bookId);

    public int updateHistoryStatus(int historyId, StatusEnum status);

    public int getHistoryId(int userId, int bookId);

    public int getCollectionCount(int userId);

    public List<BookCollection> findCollections(int userId,
            Pagination pagination);

    public int deleteCollection(int bookId);

    public int getHistoryCount(int userId, String status);

    public List<BorrowHistory> findBorrowHistory(int userId,
            Pagination pagination, String status);

    public int insert(User user);

    public User getUserById(int id);

    public int update(User user);

}
