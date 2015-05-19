package com.tyut.book;

import java.util.HashMap;


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

    public static String getContextPath() {
        return AppContext.contextPath;
    }

    public static void setContextPath(String contextPath) {
        AppContext.contextPath = contextPath;
    }

}
