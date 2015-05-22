package com.tyut.book.dao;

import java.util.List;

import com.tyut.book.model.Book;
import com.tyut.book.model.Category;
import com.tyut.book.model.Pagination;

public interface BookDao {

    public int create(Book book);

    public List<Category> findAllCategory();

    public int getCategoryIdByName(String CategoryName);

    public int getMybookCount(int userId, String loanStatus, String keyWord, int categoryId);

    public List<Book> findMyBook(int userId, Pagination pagination, String loanStatus,
            String keyWord, int categoryId);

}
