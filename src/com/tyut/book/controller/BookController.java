package com.tyut.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tyut.book.Constants;
import com.tyut.book.model.Book;
import com.tyut.book.model.Pagination;
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
        mav.setView(super.getRedirectView("/user/homepage"));
        return mav;
    }

    @RequestMapping(value = "/my_book", method = RequestMethod.GET)
    public ModelAndView goMyBookPage(
                @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                @RequestParam(value = "keyWord", defaultValue = "") String keyWord,
                @RequestParam(value = "categoryId", defaultValue = "0") int categoryId,
                @RequestParam(value = "loanStatus", defaultValue = "all") String loanStatus
            ) {
        ModelAndView mav = new ModelAndView();

        int totalCount = bookService.getMyBookCount(super.getUserId(), loanStatus, keyWord, categoryId);
        Pagination pagination = new Pagination();
        pagination.setTotalCount(totalCount);
        pagination.setCurrentPage(currentPage);

        List<Book> bookList = bookService.findMyBook(super.getUserId(), pagination, loanStatus, keyWord, categoryId);

        mav.addObject("currentPage", pagination.getCurrentPage());
        mav.addObject("pageList", pagination.getPageList());
        mav.addObject("totalPage", pagination.getTotalPage());
        mav.addObject("keyWord", keyWord);
        mav.addObject("categoryId", categoryId);
        mav.addObject("loanStatus", loanStatus);
        mav.addObject("bookList", bookList);

        mav.setViewName(Constants.MY_BOOK_JSP);
        return mav;
    }

}
