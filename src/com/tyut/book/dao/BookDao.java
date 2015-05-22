package com.tyut.book.dao;

import java.util.List;

import com.tyut.book.model.Book;
import com.tyut.book.model.Category;

public interface BookDao {

    public int create(Book book);

    public List<Category> findAllCategory();

    public int getCategoryIdByName(String CategoryName);

}
