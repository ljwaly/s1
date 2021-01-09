package com.ljw.spring.source.s1.cglib;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class CglibCallBackFilterDemo implements CallbackFilter {

    /**
     * 自定义代理策略
     *
     * @param method
     * @return
     */
    @Override
    public int accept(Method method) {
        if ("doSomething0".equalsIgnoreCase(method.getName())) {
            return 3;
        }
        if ("doSomething1".equalsIgnoreCase(method.getName())) {
            return 1;
        }
        if ("doSomething2".equalsIgnoreCase(method.getName())) {
            return 2;
        }
        if ("com.ljw.controller.xx".equalsIgnoreCase(method.getName())) {
            return 4;
        }

        return 3;
    }
}
