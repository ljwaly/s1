package com.ljw.spring.source.s1.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理对象
 */
public class Parent implements InvocationHandler {

    private Object object;

    public Parent (Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();

        /**
         * 调用被代理对象中的方法
         */
        Object result = method.invoke(object, args);

        after();
        return result;
    }

    public void before(){
        System.out.println("==========我是小明的父母，有小明照片，帮效应找对象");
    }

    public void after(){
        System.out.println("==========我是小明的父母，帮小明结婚，结婚后带孩子");
    }
}
