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

    @Override
    public int getAvailableBookCount(int userId, String keyWord, int categoryId) {
        return bookDao.getAvailableBookCount(userId, keyWord, categoryId);
    }

    @Override
    public List<Book> findAvailableBook(int userId, Pagination pagination, String keyWord,
            int categoryId) {
        return bookDao.findAvailableBook(userId, pagination, keyWord, categoryId);
    }

    @Override
    public Book getById(int id) {
        return bookDao.getById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public int deleteBook(int id) {
        return bookDao.deleteBook(id);
    }

}
