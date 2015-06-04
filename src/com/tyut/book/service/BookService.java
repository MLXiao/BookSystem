package com.tyut.book.service;

import java.util.List;

import com.tyut.book.model.Book;
import com.tyut.book.model.Category;
import com.tyut.book.model.Pagination;

public interface BookService {

    public void addBook(Book book);

    public List<Category> findAllCategory();

    public int getMyBookCount(int userId, String loanStatus, String keyWord, int categoryId);

    public List<Book> findMyBook(int userId, Pagination pagination, String loanStatus,
            String keyWord, int categoryId);

    public int getAvailableBookCount(int userId, String keyWord, int categoryId);

    public List<Book> findAvailableBook(int userId, Pagination pagination, String keyWord,
            int categoryId);

    public Book getById(int id);

    public int updateBook(Book book);

    public int deleteBook(int id);

}
