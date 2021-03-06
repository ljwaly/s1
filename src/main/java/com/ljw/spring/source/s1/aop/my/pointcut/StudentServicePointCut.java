package com.ljw.spring.source.s1.aop.my.pointcut;

import com.ljw.spring.source.s1.annotation.EasyCache;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname StudentServicePointCut
 * @Description TODO
 * @Author Jack
 * Date 2021/1/19 14:47
 * Version 1.0
 */
@Component
public class StudentServicePointCut implements Pointcut, MethodMatcher {
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
    public boolean matches(Method method, Class<?> targetClass) {
        //拿原始方法对象，这个方法上才有注解
        Method specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);

        /**
         * method如果是jdk代理，就是接口，接口上面没有注解
         *
         * specificMethod是通过AopUtil拿到的真正的方法
         */
        if(AnnotatedElementUtils.hasAnnotation(specificMethod, EasyCache.class)) {

            boolean annotationPresent = method.isAnnotationPresent(EasyCache.class);
            System.out.println("========annotationPresent=======" + annotationPresent);

            System.out.println("matches method hashcode-->" + method.hashCode());
            return true;
        }
        return false;
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
        if(method.getName().equalsIgnoreCase("eat")) {
            List<Object> objects = Arrays.asList(args);
            String param = (String)objects.get(0);
            if("Jack".equalsIgnoreCase(param)) {
                return true;
            }
        }
        return false;
    }
}
