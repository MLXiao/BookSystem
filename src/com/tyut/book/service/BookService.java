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

}
