package com.tyut.book.dao.impl.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.tyut.book.dao.BookDao;
import com.tyut.book.model.Book;

public class BookDaoImpl extends SqlSessionDaoSupport implements BookDao {

    private static final String CLASS_NAME = Book.class.getName();
    private static final String SQL_ID_Create = ".create";
    @Override
    public int Create(Book book) {
        return getSqlSession().insert(CLASS_NAME + SQL_ID_Create, book);
    }

}
