package com.tyut.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tyut.book.Constants;
import com.tyut.book.model.VerificationCode;
import com.tyut.book.service.UserService;

@Controller
@RequestMapping(value = Constants.APP_URL_USER_PREFIX)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(Constants.HOMEPAGE_JSP);
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView verifyLogin() {
        ModelAndView mav = new ModelAndView();
        //TODO
        return mav;
    }

    @RequestMapping(value = "/update_code", method = RequestMethod.POST)
    @ResponseBody
    public String updateVerificationCode() {
        VerificationCode vCode = new VerificationCode(90,30,4,80);
        try {
            Thread.sleep(200);
            //TODO 此处需要修改
            String path = "F://git/BookSystem/WebContent/static/images/verify_code.png";
            vCode.write(path);
            return vCode.getCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
