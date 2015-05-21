package com.tyut.book.dao.impl.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.tyut.book.dao.UserDao;
import com.tyut.book.model.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    private static final String CLASS_NAME = User.class.getName();
    private static final String SQL_ID_FIND_BY_NAME = ".findByName";

    @Override
    public User findByName(String username) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_FIND_BY_NAME, username);
    }

}
