package com.tyut.book.service.impl;

import java.util.List;

import com.tyut.book.Constants;
import com.tyut.book.dao.BookDao;
import com.tyut.book.dao.MessageDao;
import com.tyut.book.dao.UserDao;
import com.tyut.book.exception.ParameterException;
import com.tyut.book.exception.ServiceException;
import com.tyut.book.model.Book;
import com.tyut.book.model.BookCollection;
import com.tyut.book.model.BorrowHistory;
import com.tyut.book.model.LoanStatusEnum;
import com.tyut.book.model.Message;
import com.tyut.book.model.MessageTypeEnum;
import com.tyut.book.model.Pagination;
import com.tyut.book.model.StatusEnum;
import com.tyut.book.model.User;
import com.tyut.book.service.UserService;
import com.tyut.book.util.SessionUtil;
import com.tyut.book.util.StringUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private MessageDao messageDao;
    private BookDao bookDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public User login(String username, String password, String verifyCode) throws ServiceException, ParameterException {

        ParameterException paramException = new ParameterException();

        if(!verifyCode.equalsIgnoreCase(SessionUtil.getSessionAttribute(Constants.VERIFY_CODE).toString())) {
            paramException.addError("verifyCode", "验证码错误");
            throw paramException;
        }

        User user = userDao.findByName(username);

        if (user == null) {
            throw new ServiceException("用户名或密码错误!");
        }

        if (!StringUtil.toMD5String(password).equals(user.getPassword())) {
            throw new ServiceException("用户名或密码错误!");
        }

        return user;
    }

    @Override
    public int collectBook(int userId, int bookId) {
        return userDao.collectBook(userId, bookId);
    }

    @Override
    public boolean isBookCollected(int userId, int bookId) {
        return userDao.getBookCollection(userId, bookId) == null ? false : true;
    }

    @Override
    public int requeirBorrowBook(int userId, int bookId) {

        int result = 0;

        Book book = bookDao.getById(bookId);

        Message message = new Message();
        message.setSenderId(userId);
        message.setReceiverId(book.getOwnerId());
        message.setBookId(bookId);
        message.setType(MessageTypeEnum.borrow_info);

        result = messageDao.create(message);

        result = bookDao.updateStatus(bookId ,LoanStatusEnum.loaning);

        result = userDao.createBorrowHistory(userId, bookId);

        return result;
    }

    @Override
    public int getMessageCount(int userId) {
        return messageDao.getMessageCount(userId);
    }

    @Override
    public List<Message> findMessages(int userId, String status) {
        return messageDao.findMessages(userId, status);
    }

    @Override
    public boolean dealMessage(int messageId, boolean result) {

        Message message = messageDao.getById(messageId);
        message.setResult(result);

        Message returnMessage = new Message();
        returnMessage.setBookId(message.getBookId());
        returnMessage.setSenderId(message.getReceiverId());
        returnMessage.setReceiverId(message.getSenderId());

        int historyId = 0;

        switch (message.getType().toString()) {
        case "warn" :
            break;
        case "notice" :
            break;
        case "borrow_info" :
            if (message.getResult()) {
                returnMessage.setType(MessageTypeEnum.confirm_borrow);
            } else {
                returnMessage.setType(MessageTypeEnum.refuse_borrow);
            }
            messageDao.create(returnMessage);
            break;
        case "return_info" :
            returnMessage.setType(MessageTypeEnum.confirm_return);
            messageDao.create(returnMessage);
            break;
        case "confirm_borrow" :
            bookDao.updateStatus(message.getBookId(), LoanStatusEnum.loaned);
            bookDao.updateCurrentOwner(message.getBookId(), message.getReceiverId());
            historyId = userDao.getHistoryId(message.getReceiverId(), message.getBookId());
            userDao.updateHistoryStatus(historyId, StatusEnum.succeed);
            break;
        case "refuse_borrow" :
            bookDao.updateStatus(message.getBookId(), LoanStatusEnum.not_loaned);
            historyId = userDao.getHistoryId(message.getReceiverId(), message.getBookId());
            userDao.updateHistoryStatus(historyId, StatusEnum.failed);
            break;
        case "confirm_return" :
            bookDao.updateStatus(message.getBookId(), LoanStatusEnum.not_loaned);
            bookDao.updateCurrentOwner(message.getBookId(), message.getSenderId());
            historyId = userDao.getHistoryId(message.getReceiverId(), message.getBookId());
            userDao.updateHistoryStatus(historyId, StatusEnum.returned);
            break;
        default:
            break;
        }

        message.setResult(result);
        messageDao.update(message);

        return true;
    }

    @Override
    public int getCollectionCount(int userId) {
        return userDao.getCollectionCount(userId);
    }

    @Override
    public List<BookCollection> findCollections(int userId,
            Pagination pagination) {
        return userDao.findCollections(userId, pagination);
    }

    @Override
    public int deleteCollection(int bookId) {
        return userDao.deleteCollection(bookId);
    }

    @Override
    public int getHistoryCount(int userId, String status) {
        return userDao.getHistoryCount(userId, status);
    }

    @Override
    public List<BorrowHistory> findBorrowHistory(int userId,
            Pagination pagination, String status) {
        return userDao.findBorrowHistory(userId, pagination, status);
    }

    @Override
    public int returnBook(int userId, int bookId) {
        int result = 0;

        Book book = bookDao.getById(bookId);

        Message message = new Message();
        message.setSenderId(userId);
        message.setReceiverId(book.getOwnerId());
        message.setBookId(bookId);
        message.setType(MessageTypeEnum.return_info);

        result = messageDao.create(message);

        int historyId = userDao.getHistoryId(userId, bookId);

        result = userDao.updateHistoryStatus(historyId, StatusEnum.returning);

        return result;
    }

    @Override
    public User register(User user) {
        String password = StringUtil.toMD5String(user.getPassword());
        user.setPassword(password);
        int id = userDao.insert(user);
        return userDao.getUserById(id);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

}
