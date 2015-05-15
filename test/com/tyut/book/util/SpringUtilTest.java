package com.tyut.book.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringUtilTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void getBeanTest() {
        SqlSessionTemplate template = (SqlSessionTemplate) SpringUtil.getBean("sqlSessionTemplate");
        Assert.assertNotNull(template);
    }
}
