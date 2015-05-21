package com.tyut.book.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyut.book.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringUtilTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void testGetBean() {
        UserService userService = (UserService) SpringUtil.getBean("userService");
        Assert.assertNotNull(userService);
    }
}
