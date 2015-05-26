package com.tyut.book.dao;

import java.util.List;

import com.tyut.book.model.Message;

public interface MessageDao {

    public int create(Message message);

    public int getMessageCount(int userId);

    public List<Message> findMessages(int userId, String status);

    public Message getById(int messageId);

    public int update(Message message);

}
