package com.tyut.book.service;

import java.util.List;

import com.tyut.book.model.Book;
import com.tyut.book.model.Category;

public interface BookService {

    public void addBook(Book book);

    public List<Category> findAllCategory();

}
