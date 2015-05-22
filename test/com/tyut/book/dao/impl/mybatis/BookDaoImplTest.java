package com.tyut.book.dao.impl.mybatis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tyut.book.dao.BookDao;
import com.tyut.book.model.Category;
import com.tyut.book.util.SpringUtil;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BookDaoImplTest {

    private BookDao bookDao;

    @Before
    public void setUp() {
        bookDao = (BookDao) SpringUtil.getBean("bookDao");
    }

    @Test
    public void testfindAllCategory() {
        for (Category category : bookDao.findAllCategory()) {
            System.out.println(category.getName());
        }
    }

    @Test
    public void testGetCategoryIdByName() {
        System.out.println(bookDao.getCategoryIdByName("计算机"));
    }

}
