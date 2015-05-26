package com.tyut.book.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tyut.book.model.Book;
import com.tyut.book.model.Pagination;
import com.tyut.book.service.BookService;
import com.tyut.book.util.SpringUtil;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BookServiceImplTest {

    private BookService bookService;

    @Before
    public void setUp() {
        bookService = (BookService) SpringUtil.getBean("bookService");
    }

    @Test
    public void testGetMyBookCount() {
        System.out.println(bookService.getMyBookCount(2, "all", "", 0));

        System.out.println(bookService.getMyBookCount(2, "loaned", "", 0));
    }

    @Test
    public void testfindMyBook() {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(1);
        pagination.setPageSize(5);
        pagination.setTotalCount(bookService.getMyBookCount(2, "all", "", 0));
        for(Book book : bookService.findMyBook(2, pagination, "all", "", 0)) {
            System.out.println(book.getName());
        }
    }

    @Test
    public void testGetAvailableBookCount() {
        System.out.println(bookService.getAvailableBookCount(0, "", 0));
    }

    @Test
    public void testfindAvailableBook() {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(1);
        pagination.setPageSize(16);
        pagination.setTotalCount(bookService.getAvailableBookCount(0, "", 0));
        for(Book book : bookService.findAvailableBook(0, pagination, "", 0)) {
            System.out.println(book.getName());
        }
    }

    @Test
    public void testGetById() {
        System.out.println(bookService.getById(10).getName());
    }

}
