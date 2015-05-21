package com.tyut.book.exception;

import java.util.HashMap;
import java.util.Map;

public class ParameterException extends Exception {

    private static final long serialVersionUID = -4765602307192786743L;

    Map<String, String> errorFields = new HashMap<String, String>();

    public Map<String, String> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(Map<String, String> errorFields) {
        this.errorFields = errorFields;
    }

    public void addError(String paramName, String errorMessage) {
        errorFields.put(paramName, errorMessage);
    }

    public boolean hasError() {
        return !errorFields.isEmpty();
    }
}
