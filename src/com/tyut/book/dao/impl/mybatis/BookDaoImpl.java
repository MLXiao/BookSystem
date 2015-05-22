package com.tyut.book.dao.impl.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.tyut.book.dao.BookDao;
import com.tyut.book.model.Book;
import com.tyut.book.model.Category;

public class BookDaoImpl extends SqlSessionDaoSupport implements BookDao {

    private static final String CLASS_NAME = Book.class.getName();
    private static final String SQL_ID_CREATE = ".create";
    private static final String SQL_ID_FIND_ALL_CATEGORY = ".findAllCategory";
    private static final String SQL_ID_GET_CATEGORY_ID_BY_NAME = ".getCategoryIdByName";

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

}
