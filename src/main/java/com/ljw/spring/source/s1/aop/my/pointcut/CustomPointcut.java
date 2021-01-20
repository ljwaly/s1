package com.ljw.spring.source.s1.aop.my.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CustomPointcut implements Pointcut, MethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        String classFullName = targetClass.getName();

        /**
         * 针对com.ljw.spring.source.s1.service这个包下所有的类方法都进行匹配
         */
        if(classFullName.startsWith("com.ljw.spring.source.s1.service")){
            return true;
        }


        return false;
    }

    @Override
    public ClassFilter getClassFilter() {

        /**
         * 类匹配，直接返回true
         */
        return ClassFilter.TRUE;
    }
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }


    @Override
    public boolean isRuntime() {
        /**
         * 返回false，参数匹配不生效
         */
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }




}
