package com.tyut.book.service.impl;

import java.util.List;

import com.tyut.book.dao.BookDao;
import com.tyut.book.model.Book;
import com.tyut.book.model.Category;
import com.tyut.book.service.BookService;

public class BookServiceImpl implements BookService {

    private BookDao bookDao;
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void addBook(Book book) {
        bookDao.create(book);
    }

    @Override
    public List<Category> findAllCategory() {
        return bookDao.findAllCategory();
    }

}
