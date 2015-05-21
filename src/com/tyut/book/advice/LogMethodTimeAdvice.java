package com.tyut.book.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.tyut.book.AppContext;

public class LogMethodTimeAdvice implements MethodInterceptor {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object returnValue = methodInvocation.proceed();
        String methodName = methodInvocation.getMethod().getName();

        long endTime = System.currentTimeMillis();

        StringBuffer sb = new StringBuffer();
        sb.append(AppContext.getInstance().getUserName());
        sb.append(":");
        sb.append(methodInvocation.getMethod().getDeclaringClass().getSimpleName());
        sb.append(":");
        sb.append(methodName);
        sb.append(":");
        sb.append(endTime - startTime);

        logger.info(sb.toString());

        return returnValue;
    }

}
