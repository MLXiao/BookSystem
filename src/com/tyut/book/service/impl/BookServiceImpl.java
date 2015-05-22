package com.tyut.book.service.impl;

import java.util.List;

import com.tyut.book.dao.BookDao;
import com.tyut.book.model.Book;
import com.tyut.book.model.Category;
import com.tyut.book.model.Pagination;
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

    @Override
    public int getMyBookCount(int userId, String loanStatus, String keyWord, int categoryId) {
        return bookDao.getMybookCount(userId, loanStatus, keyWord, categoryId);
    }

    @Override
    public List<Book> findMyBook(int userId, Pagination pagination, String loanStatus,
            String keyWord, int categoryId) {
        return bookDao.findMyBook(userId, pagination, loanStatus, keyWord, categoryId);
    }

}
