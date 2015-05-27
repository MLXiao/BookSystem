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

import com.tyut.book.Constants;
import com.tyut.book.model.User;
import com.tyut.book.service.UserService;
import com.tyut.book.util.PathUtil;
import com.tyut.book.util.SpringUtil;

public class SessionFilter implements Filter {

    private String notNeedLoginPages = "";

    public SessionFilter() {
    }

    @Override
    public void destroy() {
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();


        UserService userService = (UserService) SpringUtil.getBean("userService");
        User user = (User) session.getAttribute(Constants.USER);
        int messageCount = 0;
        if (user != null) {
            messageCount = userService.getMessageCount(user.getId());
        }
        session.setAttribute(Constants.MESSAGE_COUNT, messageCount);

        String requestUri = request.getRequestURI();
        String relativeUri = requestUri.substring(request.getContextPath().length());

        String[] pages = notNeedLoginPages.split(",");

        boolean notNeedLogin = false;

        for (String page : pages) {
            if (relativeUri.equals(page)) {
                notNeedLogin = true;
                break;
            }
        }

        if (notNeedLogin || session.getAttribute(Constants.USER) != null) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(PathUtil.getFullPath("/user/homepage#login"));
        }
    }
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        notNeedLoginPages = fConfig.getServletContext().getInitParameter("notNeedLoginPages");
    }

}
