package com.tyut.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tyut.book.Constants;
import com.tyut.book.model.Book;
import com.tyut.book.service.BookService;
import com.tyut.book.util.StringUtil;

@Controller
@RequestMapping(value = Constants.APP_URL_BOOK_PREFIX)
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/add_book", method = RequestMethod.GET)
    public ModelAndView goAddBookPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject(Constants.ALL_CATEGORY, bookService.findAllCategory());
        mav.setViewName(Constants.ADD_BOOK_JSP);
        return mav;
    }

    @RequestMapping(value = "/add_book", method = RequestMethod.POST)
    public ModelAndView AddBook(Book book, @RequestParam(value="coverBase64") String coverBase64) {
        ModelAndView mav = new ModelAndView();
        book.setOwnerId(super.getUserId());
        book.setCurrentOwnerId(super.getUserId());
        book.setCover(StringUtil.base64ToByteArray(coverBase64));
        bookService.addBook(book);
        super.setSessionAttribute(Constants.FLASH_MESSAGE, "上架成功!");
        mav.setViewName(Constants.HOMEPAGE_JSP);
        mav.setView(super.getRedirectView("/user/homepage"));
        return mav;
    }

}
