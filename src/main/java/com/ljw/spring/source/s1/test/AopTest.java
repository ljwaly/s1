package com.ljw.spring.source.s1.test;

import com.ljw.spring.source.s1.methodfind.SubClass;
import org.junit.Test;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class AopTest {

    @Test
    public void test1(){
        Method[] declaredMethods = ReflectionUtils.getDeclaredMethods(SubClass.class);

        Method method = ClassUtils.getMethod(SubClass.class, "method", new Class[]{Object.class});

    }
}
