package com.tyut.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tyut.book.Constants;
import com.tyut.book.model.Book;
import com.tyut.book.model.Pagination;
import com.tyut.book.service.BookService;
import com.tyut.book.service.UserService;
import com.tyut.book.util.StringUtil;

@Controller
@RequestMapping(value = Constants.APP_URL_BOOK_PREFIX)
public class BookController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/add_book", method = RequestMethod.GET)
    public ModelAndView goAddBookPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(Constants.SAVE_BOOK_JSP);
        return mav;
    }

    @RequestMapping(value = "/edit_book/{id}", method = RequestMethod.GET)
    public ModelAndView goEditBookPage(@PathVariable int id) {
        ModelAndView mav = new ModelAndView();
        Book book = bookService.getById(id);
        mav.addObject("book", book);
        mav.setViewName(Constants.SAVE_BOOK_JSP);
        return mav;
    }

    @RequestMapping(value = "/save_book", method = RequestMethod.POST)
    public ModelAndView saveBook(Book book, @RequestParam(value="coverBase64") String coverBase64) {
        ModelAndView mav = new ModelAndView();
        System.out.println(book.getId());
        book.setOwnerId(super.getUserId());
        book.setCurrentOwnerId(super.getUserId());
        book.setCover(StringUtil.base64ToByteArray(coverBase64));

        if (book.getId() == 0) {
            bookService.addBook(book);
            super.setSessionAttribute(Constants.FLASH_MESSAGE, "上架成功!");
        } else {
            bookService.updateBook(book);
            super.setSessionAttribute(Constants.FLASH_MESSAGE, "修改成功!");
        }

        mav.setView(super.getRedirectView("/book/my_book"));
        return mav;
    }

    @RequestMapping(value = "/delete_book", method = RequestMethod.POST)
    public ModelAndView deleteBook(@RequestParam(value="id") int id) {
        ModelAndView mav = new ModelAndView();
        bookService.deleteBook(id);
        super.setSessionAttribute(Constants.FLASH_MESSAGE, "下架成功!");
        mav.setView(super.getRedirectView("/book/my_book"));
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

        mav.addObject("pagination", pagination);
        mav.addObject("keyWord", keyWord);
        mav.addObject("categoryId", categoryId);
        mav.addObject("loanStatus", loanStatus);
        mav.addObject("bookList", bookList);

        mav.setViewName(Constants.MY_BOOK_JSP);
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView goBookDetailPage(@PathVariable int id) {
        ModelAndView mav = new ModelAndView();

        Book book = bookService.getById(id);

        boolean isBookCollected = userService.isBookCollected(getUserId(), id);

        mav.addObject("book", book);
        mav.addObject("isBookCollected", isBookCollected);

        mav.setViewName(Constants.BOOK_DETAIL_JSP);
        return mav;
    }

}
