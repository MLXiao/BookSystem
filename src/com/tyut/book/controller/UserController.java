package com.tyut.book.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tyut.book.Constants;
import com.tyut.book.exception.ParameterException;
import com.tyut.book.exception.ServiceException;
import com.tyut.book.model.User;
import com.tyut.book.model.VerificationCode;
import com.tyut.book.service.UserService;

@Controller
@RequestMapping(value = Constants.APP_URL_USER_PREFIX)
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(Constants.HOMEPAGE_JSP);
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> verifyLogin(@RequestBody Map<String, String> inputMap) {
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

}
