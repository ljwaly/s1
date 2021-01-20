package com.ljw.spring.source.s1.aop.my.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Classname CustomAdvice
 * @Description TODO
 * @Author Jack
 * Date 2021/1/13 15:13
 * Version 1.0
 */
public class CustomAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("=========CustomAdvice.invoke-----------1111");
        return invocation.proceed();
    }
}
