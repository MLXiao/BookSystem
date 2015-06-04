package com.tyut.book.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tyut.book.Constants;
import com.tyut.book.exception.ParameterException;
import com.tyut.book.exception.ServiceException;
import com.tyut.book.model.Book;
import com.tyut.book.model.BookCollection;
import com.tyut.book.model.BorrowHistory;
import com.tyut.book.model.Message;
import com.tyut.book.model.Pagination;
import com.tyut.book.model.User;
import com.tyut.book.model.VerificationCode;
import com.tyut.book.service.BookService;
import com.tyut.book.service.UserService;
import com.tyut.book.util.StringUtil;

@Controller
@RequestMapping(value = Constants.APP_URL_USER_PREFIX)
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public ModelAndView goHomepage(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "keyWord", defaultValue = "") String keyWord,
            @RequestParam(value = "categoryId", defaultValue = "0") int categoryId
            ) {
        ModelAndView mav = new ModelAndView();
        if (super.getSessionAttribute(Constants.ALL_CATEGORY) == null) {
            super.setSessionAttribute(Constants.ALL_CATEGORY, bookService.findAllCategory());
        }

        int totalCount = bookService.getAvailableBookCount(super.getUserId(), keyWord, categoryId);

        Pagination pagination = new Pagination();
        pagination.setTotalCount(totalCount);
        pagination.setPageSize(16);
        pagination.setCurrentPage(currentPage);

        List<Book> bookList = bookService.findAvailableBook(super.getUserId(), pagination, keyWord, categoryId);

        mav.addObject("pagination", pagination);
        mav.addObject("keyWord", keyWord);
        mav.addObject("categoryId", categoryId);
        mav.addObject("bookList", bookList);

        mav.setViewName(Constants.HOMEPAGE_JSP);
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(@RequestBody Map<String, String> inputMap) {
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            User user = userService.login(inputMap.get("username"), inputMap.get("password"), inputMap.get("verifyCode"));
            super.setSessionAttribute(Constants.USER, user);
        } catch (ParameterException pe) {
             Map<String, String> errorFields = pe.getErrorFields();
             for (String paramName : errorFields.keySet()) {
                 resultMap.put(paramName, errorFields.get(paramName));
             }
        } catch (ServiceException se) {
            resultMap.put("service", se.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView goRegisterPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(Constants.REGISTER);
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(User user, @RequestParam(value="avatarBase64") String avatarBase64) {
        ModelAndView mav = new ModelAndView();

        user.setAvatar(StringUtil.base64ToByteArray(avatarBase64));
        userService.register(user);

        super.setSessionAttribute(Constants.USER, user);

        mav.setView(getRedirectView("/user/homepage"));
        return mav;
    }

    @RequestMapping(value = "/update_vcode", method = RequestMethod.POST)
    @ResponseBody
    public String updateVerificationCode() {
        VerificationCode vCode = new VerificationCode(90,30,4,80);
        try {
            Thread.sleep(180);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.setSessionAttribute(Constants.VERIFY_CODE, vCode.getCode());
        return vCode.getImgBase64String();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView mav = new ModelAndView();
        super.removeSessionAttribute(Constants.USER);
        mav.setView(getRedirectView("/user/homepage"));
        return mav;
    }

    @RequestMapping(value = "/collect_book", method = RequestMethod.POST)
    @ResponseBody
    public String collectBook(@RequestBody String bookId) {
        String result = "fail";
        int row = userService.collectBook(getUserId(), Integer.parseInt(bookId));
        if (row > 0) {
            result = "ok";
        }
        return result;
    }

    @RequestMapping(value = "require_borrow", method = RequestMethod.POST)
    @ResponseBody
    public String requireBorrowBook(@RequestBody String bookId) {
        String result = "fail";

        int i = userService.requeirBorrowBook(getUserId(), Integer.valueOf(bookId));

        if (i > 0) {
            result = "ok";
        }

        return result;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ModelAndView goMessagePage(@RequestParam(value="status", defaultValue="new") String status) {
        ModelAndView mav = new ModelAndView();

        List<Message> messages = userService.findMessages(getUserId(), status);

        mav.addObject("messages", messages);
        mav.addObject("status", status);
        mav.setViewName(Constants.MESSAGE_JSP);
        return mav;
    }

    @RequestMapping(value = "/deal_message", method = RequestMethod.POST)
    public ModelAndView dealMessage(
            @RequestParam(value = "messageId", defaultValue="0") int messageId,
            @RequestParam(value = "result", defaultValue="false") boolean result
            ) {
        ModelAndView mav = new ModelAndView();

        userService.dealMessage(messageId, result);

        mav.setView(getRedirectView("/user/message"));
        return mav;
    }

    @RequestMapping(value = "my_collect", method = RequestMethod.GET)
    public ModelAndView goMyCollectPage(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        ModelAndView mav = new ModelAndView();

        int totalCount = userService.getCollectionCount(getUserId());

        Pagination pagination = new Pagination();
        pagination.setTotalCount(totalCount);
        pagination.setCurrentPage(currentPage);

        List<BookCollection> collections = userService.findCollections(getUserId(), pagination);

        mav.addObject("pagination", pagination);
        mav.addObject("collections", collections);
        mav.setViewName(Constants.MY_COLLECT);
        return mav;
    }

    @RequestMapping(value = "/cancel_collect/{bookId}", method = RequestMethod.GET)
    public ModelAndView cancelCollect(@PathVariable int bookId) {
        ModelAndView mav = new ModelAndView();

        userService.deleteCollection(bookId);

        mav.setView(getRedirectView("/user/my_collect"));
        return mav;
    }

    @RequestMapping(value="/my_borrow", method = RequestMethod.GET)
    public ModelAndView goMyBorrowPage(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "loanStatus", defaultValue = "succeed") String status
            ) {
        ModelAndView mav = new ModelAndView();

        int totalCount = userService.getHistoryCount(getUserId(), status);
        Pagination pagination = new Pagination();
        pagination.setTotalCount(totalCount);
        pagination.setCurrentPage(currentPage);

        List<BorrowHistory> histories = new ArrayList<BorrowHistory>();
        histories = userService.findBorrowHistory(getUserId(), pagination, status);

        mav.addObject("pagination", pagination);
        mav.addObject("loanStatus", status);
        mav.addObject("histories", histories);

        mav.setViewName(Constants.MY_BORROW);
        return mav;
    }

    @RequestMapping(value = "/return_book", method = RequestMethod.GET)
    public ModelAndView returnBook(@RequestParam(value="bookId", defaultValue = "0") int bookId) {
        ModelAndView mav = new ModelAndView();

        userService.returnBook(getUserId(), bookId);

        mav.setView(getRedirectView("/user/my_borrow"));
        return mav;
    }

    @RequestMapping(value = "/show_user/{id}", method = RequestMethod.GET)
    public ModelAndView goShowUserPage(@PathVariable int id) {
        ModelAndView mav = new ModelAndView();

        User user = userService.getUserById(id);
        mav.addObject("user", user);

        mav.setViewName(Constants.SHOW_USER);
        return mav;
    }

    @RequestMapping(value = "/edit_user/{id}", method = RequestMethod.GET)
    public ModelAndView goEditUserPage(@PathVariable int id) {
        ModelAndView mav = new ModelAndView();

        User user = userService.getUserById(id);
        mav.addObject("user", user);

        mav.setViewName(Constants.EDIT_USER);

        return mav;
    }

    @RequestMapping(value = "/edit_user", method = RequestMethod.POST)
    public ModelAndView editUser(User user, @RequestParam(value="avatarBase64") String avatarBase64) {
        ModelAndView mav = new ModelAndView();

        user.setAvatar(StringUtil.base64ToByteArray(avatarBase64));

        userService.update(user);

        super.setSessionAttribute(Constants.USER, user);

        mav.setView(getRedirectView("/user/show_user/" + user.getId()));
        return mav;
    }

}
