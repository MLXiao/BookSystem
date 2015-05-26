package com.tyut.book.dao.impl.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.tyut.book.dao.UserDao;
import com.tyut.book.model.BookCollection;
import com.tyut.book.model.StatusEnum;
import com.tyut.book.model.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    private static final String CLASS_NAME = User.class.getName();
    private static final String SQL_ID_FIND_BY_NAME = ".findByName";
    private static final String SQL_ID_COLLECT_BOOK = ".collectBook";
    private static final String SQL_ID_GET_BOOK_COLLECTION = ".getBookCollection";
    private static final String SQL_ID_CREATE_BORROW_HISTORY = ".createBorrowHistory";
    private static final String SQL_ID_UPDATE_HISTORY_STATUS = ".updateHistoryStatus";
    private static final String SQL_ID_GET_HISTORY_ID = ".getHistoryId";

    @Override
    public User findByName(String username) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_FIND_BY_NAME, username);
    }

    @Override
    public int collectBook(int userId, int bookId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("bookId", bookId);
        return getSqlSession().insert(CLASS_NAME + SQL_ID_COLLECT_BOOK, paramMap);
    }

    @Override
    public BookCollection getBookCollection(int userId, int bookId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("bookId", bookId);
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_BOOK_COLLECTION, paramMap);
    }

    @Override
    public int createBorrowHistory(int userId, int bookId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("bookId", bookId);
        return getSqlSession().insert(CLASS_NAME + SQL_ID_CREATE_BORROW_HISTORY, paramMap);
    }

    @Override
    public int updateHistoryStatus(int historyId, StatusEnum status) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("messageId", historyId);
        paramMap.put("status", status);
        return getSqlSession().update(CLASS_NAME + SQL_ID_UPDATE_HISTORY_STATUS, paramMap);
    }

    @Override
    public int getHistoryId(int userId, int bookId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("bookId", bookId);
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_HISTORY_ID, paramMap);
    }

}
