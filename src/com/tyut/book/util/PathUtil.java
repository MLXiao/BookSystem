package com.tyut.book.util;

import com.tyut.book.AppContext;
import com.tyut.book.Constants;

public class PathUtil {

    public static String getFullPath(String path) {
        if (path == null) {
            path = "";
        }
        String urlPrefix = Constants.APP_URL_PREFIX;
        return AppContext.getInstance().getContextPath() + urlPrefix  + path;
    }

}
