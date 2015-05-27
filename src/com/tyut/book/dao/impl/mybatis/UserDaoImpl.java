package com.tyut.book.dao.impl.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.tyut.book.dao.UserDao;
import com.tyut.book.model.BookCollection;
import com.tyut.book.model.BorrowHistory;
import com.tyut.book.model.Pagination;
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
    private static final String SQL_ID_GET_COLLECTION_COUNT = ".getCollectionCount";
    private static final String SQL_ID_FIND_COLLECTIONS = ".findCollections";
    private static final String SQL_ID_DELETE_COLLECTION = ".deleteCollection";
    private static final String SQL_ID_GET_HISTORY_COUNT = ".getHistoryCount";
    private static final String SQL_ID_FIND_BORROW_HISTORY = ".findBorrowHistory";

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
        paramMap.put("historyId", historyId);
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

    @Override
    public int getCollectionCount(int userId) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_COLLECTION_COUNT, userId);
    }

    @Override
    public List<BookCollection> findCollections(int userId,
            Pagination pagination) {
        List<BookCollection> result = new ArrayList<BookCollection>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("offSet", pagination.getOffSet());
        paramMap.put("pageSize", pagination.getPageSize());
        result = getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_COLLECTIONS, paramMap);
        return result;
    }

    @Override
    public int deleteCollection(int bookId) {
        return getSqlSession().delete(CLASS_NAME + SQL_ID_DELETE_COLLECTION, bookId);
    }

    @Override
    public int getHistoryCount(int userId, String status) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("status", status);
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_HISTORY_COUNT, paramMap);
    }

    @Override
    public List<BorrowHistory> findBorrowHistory(int userId,
            Pagination pagination, String status) {
        List<BorrowHistory> resultList = new ArrayList<BorrowHistory>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("status", status);
        paramMap.put("offSet", pagination.getOffSet());
        paramMap.put("pageSize", pagination.getPageSize());
        resultList = getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_BORROW_HISTORY, paramMap);
        return resultList;
    }

}
