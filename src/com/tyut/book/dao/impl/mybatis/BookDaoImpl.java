package com.tyut.book.dao.impl.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.tyut.book.dao.BookDao;
import com.tyut.book.model.Book;
import com.tyut.book.model.Category;
import com.tyut.book.model.LoanStatusEnum;
import com.tyut.book.model.Pagination;
import com.tyut.book.util.StringUtil;

public class BookDaoImpl extends SqlSessionDaoSupport implements BookDao {

    private static final String CLASS_NAME = Book.class.getName();
    private static final String SQL_ID_CREATE = ".create";
    private static final String SQL_ID_FIND_ALL_CATEGORY = ".findAllCategory";
    private static final String SQL_ID_GET_CATEGORY_ID_BY_NAME = ".getCategoryIdByName";
    private static final String SQL_ID_GET_MY_BOOK_COUNT = ".getMybookCount";
    private static final String SQL_ID_FIND_MY_BOOK = ".findMyBook";
    private static final String SQL_ID_GET_AVAILABLE_BOOK_COUNT = ".getAvailableBookCount";
    private static final String SQL_ID_FIND_AVAILABLE_BOOK = ".findAvailableBook";
    private static final String SQL_ID_GET_BY_ID = ".getById";
    private static final String SQL_ID_UPDATE_STATUS = ".updateStatus";

    @Override
    public int create(Book book) {
        return getSqlSession().insert(CLASS_NAME + SQL_ID_CREATE, book);
    }

    @Override
    public List<Category> findAllCategory() {
        List<Category> resultList = new ArrayList<Category>();
        resultList = getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ALL_CATEGORY);
        return resultList;
    }

    @Override
    public int getCategoryIdByName(String categoryName) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_CATEGORY_ID_BY_NAME, categoryName);
    }

    @Override
    public int getMybookCount(int userId, String loanStatus, String keyWord, int categoryId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanStatus", loanStatus);
        paramMap.put("keyWord", StringUtil.toFuzzyKeyWord(keyWord));
        paramMap.put("categoryId", categoryId);
        paramMap.put("userId", userId);
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_MY_BOOK_COUNT, paramMap);
    }

    @Override
    public List<Book> findMyBook(int userId, Pagination pagination, String loanStatus,
            String keyWord, int categoryId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanStatus", loanStatus);
        paramMap.put("keyWord", StringUtil.toFuzzyKeyWord(keyWord));
        paramMap.put("categoryId", categoryId);
        paramMap.put("userId", userId);
        paramMap.put("pageSize", pagination.getPageSize());
        paramMap.put("offSet", pagination.getOffSet());

        List<Book> resultList = new ArrayList<Book>();
        resultList = getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_MY_BOOK, paramMap);
        return resultList;
    }

    @Override
    public int getAvailableBookCount(int userId, String keyWord, int categoryId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("keyWord", StringUtil.toFuzzyKeyWord(keyWord));
        paramMap.put("categoryId", categoryId);
        paramMap.put("userId", userId);
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_AVAILABLE_BOOK_COUNT, paramMap);
    }

    @Override
    public List<Book> findAvailableBook(int userId, Pagination pagination, String keyWord,
            int categoryId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("keyWord", StringUtil.toFuzzyKeyWord(keyWord));
        paramMap.put("categoryId", categoryId);
        paramMap.put("userId", userId);
        paramMap.put("pageSize", pagination.getPageSize());
        paramMap.put("offSet", pagination.getOffSet());

        List<Book> resultList = new ArrayList<Book>();
        resultList = getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_AVAILABLE_BOOK, paramMap);
        return resultList;
    }

    @Override
    public Book getById(int id) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_BY_ID, id);
    }

    @Override
    public int updateStatus(int bookId, LoanStatusEnum status) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bookId", bookId);
        paramMap.put("status", status);
        return getSqlSession().update(CLASS_NAME + SQL_ID_UPDATE_STATUS, paramMap);
    }

}
