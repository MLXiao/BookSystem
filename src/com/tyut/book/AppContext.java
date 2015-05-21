package com.tyut.book;

import java.util.HashMap;

import com.tyut.book.model.User;


public class AppContext {

    private static final ThreadLocal<AppContext> THREAD_LOCAL = new ThreadLocal<AppContext>();
    private final HashMap<String, Object> initParams = new HashMap<String, Object>();
    private static String contextPath;

    private AppContext() {};

    public static synchronized AppContext getInstance() {
        AppContext appContext = THREAD_LOCAL.get();
        if (appContext == null) {
            appContext = new AppContext();
            THREAD_LOCAL.set(appContext);
        }
        return appContext;
    }

    public void clear() {
        AppContext appContext = THREAD_LOCAL.get();
        if (appContext != null) {
            appContext.initParams.clear();
        }
    }

    public void addObject(String key, Object value) {
        initParams.put(key, value);
    }

    public Object getObject(String key) {
        return initParams.get(key);
    }

    public void removeObject(String key) {
        initParams.remove(key);
    }

    public String getContextPath() {
        return AppContext.contextPath;
    }

    public void setContextPath(String contextPath) {
        AppContext.contextPath = contextPath;
    }

    public User getUser() {
        return (User)initParams.get(Constants.USER);
    }

    public String getUserName() {
        User user = (User) initParams.get(Constants.USER);
        if (user != null) {
            return user.getUsername();
        }
        return "";
    }

    public int getUserId() {
        User user = (User) initParams.get(Constants.USER);
        if (user != null) {
            return user.getId();
        }
        return 0 ;
    }

}
