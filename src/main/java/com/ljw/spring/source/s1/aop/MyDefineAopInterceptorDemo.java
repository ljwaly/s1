package com.ljw.spring.source.s1.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;


@Component
public class MyDefineAopInterceptorDemo implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        System.out.println("MyDefineAopInterceptorDemo.invoke");

        return invocation.proceed();
    }
}
