package com.tyut.book.dao;

import java.util.List;

import com.tyut.book.model.Book;
import com.tyut.book.model.Category;
import com.tyut.book.model.LoanStatusEnum;
import com.tyut.book.model.Pagination;

public interface BookDao {

    public int create(Book book);

    public List<Category> findAllCategory();

    public int getCategoryIdByName(String CategoryName);

    public int getMybookCount(int userId, String loanStatus, String keyWord, int categoryId);

    public List<Book> findMyBook(int userId, Pagination pagination, String loanStatus,
            String keyWord, int categoryId);

    public int getAvailableBookCount(int userId, String keyWord, int categoryId);

    public List<Book> findAvailableBook(int userId, Pagination pagination, String keyWord,
            int categoryId);

    public Book getById(int id);

    public int updateStatus(int bookId, LoanStatusEnum status);

    public int updateCurrentOwner(int bookId, int userId);

}
