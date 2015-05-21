package com.tyut.book.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tyut.book.exception.ParameterException;
import com.tyut.book.exception.ServiceException;
import com.tyut.book.service.UserService;
import com.tyut.book.util.SpringUtil;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceImplTest {

    private UserService userService;

    @Before
    public void setUp() {
        userService = (UserService) SpringUtil.getBean("userService");
    }

    @Test
    public void testLoginSuccess() {
        try {
            userService.login("aa", "a", "a");
        } catch (ServiceException | ParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
