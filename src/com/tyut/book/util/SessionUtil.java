package com.tyut.book.util;

import java.lang.reflect.Method;

import com.tyut.book.AppContext;
import com.tyut.book.Constants;

public class SessionUtil {

    private static Object getSessionInThread() {
        Object session = AppContext.getInstance().getObject(Constants.APP_CONTEXT_SESSION);
        return session;
    }
    public static void setSessionAttribute(String key, Object object) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Class<?>[] params = new Class[2];
            params[0] = String.class;
            params[1] = Object.class;

            Method method = session.getClass().getMethod("setAttribute", params);

            Object[] objects = new Object[2];
            objects[0] = key;
            objects[1] = object;

            method.invoke(session, objects);
        } catch (Exception ex) {
            new RuntimeException(ex);
        }
    }

    public static Object getSessionAttribute(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return null;
        }
        try {
            Class<?>[] params = new Class[1];
            params[0] = String.class;

            Method method = session.getClass().getMethod("getAttribute", params);

            Object[] objects = new Object[1];
            objects[0] = key;

            Object returnValue = method.invoke(session, objects);
            return returnValue;
        } catch (Exception ex) {
            new RuntimeException(ex);
        }
        return null;
    }


    public static void removeSessionAttribute(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Class<?>[] params = new Class[1];
            params[0] = String.class;

            Method method = session.getClass().getMethod("removeAttribute", params);

            Object[] objects = new Object[1];
            objects[0] = key;

            method.invoke(session, objects);

        } catch (Exception ex) {
            new RuntimeException(ex);
        }
    }

    public static void invalidate() {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Method method = session.getClass().getMethod("invalidate");
            method.invoke(session);
        } catch (Exception ex) {
            new RuntimeException(ex);
        }
    }

}
