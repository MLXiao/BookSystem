package com.tyut.book.dao.impl.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.tyut.book.dao.MessageDao;
import com.tyut.book.model.Message;

public class MessageDaoImpl extends SqlSessionDaoSupport implements MessageDao {

    private static final String CLASS_NAME = Message.class.getName();
    private static final String SQL_ID_CREATE = ".create";
    private static final String SQL_ID_GET_MESSAGE_COUNT = ".getMessageCount";
    private static final String SQL_ID_FIND_MESSAGES = ".findMessages";
    private static final String SQL_ID_GET_BY_ID = ".getById";
    private static final String SQL_ID_UPDATE = ".update";

    @Override
    public int create(Message message) {
        return getSqlSession().insert(CLASS_NAME + SQL_ID_CREATE, message);
    }

    @Override
    public int getMessageCount(int userId) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_MESSAGE_COUNT, userId);
    }

    @Override
    public List<Message> findMessages(int userId, String status) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("status", status);
        List<Message> messages = new ArrayList<Message>();
        messages = getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_MESSAGES, paramMap);
        return messages;
    }

    @Override
    public Message getById(int messageId) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_BY_ID, messageId);
    }

    @Override
    public int update(Message message) {
        return getSqlSession().update(CLASS_NAME + SQL_ID_UPDATE, message);
    }

}
