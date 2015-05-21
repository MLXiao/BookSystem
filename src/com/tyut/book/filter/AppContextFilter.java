package com.tyut.book.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tyut.book.AppContext;
import com.tyut.book.Constants;

public class AppContextFilter implements Filter {

    public AppContextFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

        AppContext appContext = AppContext.getInstance();

        appContext.setContextPath(request.getContextPath());
        appContext.addObject(Constants.APP_CONTEXT_REQUEST, request);
        appContext.addObject(Constants.APP_CONTEXT_RESPONSE, response);
        appContext.addObject(Constants.APP_CONTEXT_SESSION, session);
        appContext.addObject(Constants.USER, session.getAttribute(Constants.USER));

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
