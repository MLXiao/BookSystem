package com.tyut.book.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tyut.book.AppContext;
import com.tyut.book.model.User;
import com.tyut.book.util.PathUtil;
import com.tyut.book.util.SessionUtil;

public abstract class BaseController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        logger.error(e.getMessage(), e);
        e.printStackTrace();
        ModelAndView modelAndview = new ModelAndView(new RedirectView(AppContext.getInstance().getContextPath() + "/static/500.html"));
        return modelAndview;
    }

    protected User getUser() {
        return AppContext.getInstance().getUser();
    }

    protected String getUserName() {
        User user = getUser();
        if (user != null) {
            return user.getUsername();
        }
        return "";
    }

    protected int getUserId() {
        User user = getUser();
        if (user != null) {
            return user.getId();
        }
        return 0;
    }

    protected RedirectView getRedirectView(String path) {
        return new RedirectView(PathUtil.getFullPath(path));
    }

    protected void setSessionAttribute(String key, Object object) {
        SessionUtil.setSessionAttribute(key, object);
    }

    protected Object getSessionAttribute(String key) {
        return SessionUtil.getSessionAttribute(key);
    }

    protected void removeSessionAttribute(String key) {
        SessionUtil.removeSessionAttribute(key);
    }

    protected void invalidateSession() {
        SessionUtil.invalidate();
    }

}
