package com.tyut.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tyut.book.dao.BookDao;
import com.tyut.book.model.Book;
import com.tyut.book.service.BookService;

public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void addBook(Book book) {

    }



}
