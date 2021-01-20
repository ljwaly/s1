package com.ljw.spring.source.s1.aop.my.advisor;

import com.ljw.spring.source.s1.aop.my.advice.CustomAdvice;
import com.ljw.spring.source.s1.aop.my.pointcut.CustomPointcut;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 *
 *
 *
 * @Classname CustomAdvisor1
 * @Description TODO
 * @Author Jack
 * Date 2021/1/13 15:07
 * Version 1.0
 */
@Component
public class CustomAdvisor3 implements PointcutAdvisor, Ordered {
    @Autowired
    private CustomPointcut customPointcut;

    @Override
    public Pointcut getPointcut() {
//        return Pointcut.TRUE;
        return customPointcut;
    }

    @Override
    public Advice getAdvice() {
        return new CustomAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
