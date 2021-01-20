package com.ljw.spring.source.s1.aop.my.advisor;

import com.ljw.spring.source.s1.aop.my.advice.CustomAdvice;
import com.ljw.spring.source.s1.aop.my.pointcut.CustomPointcut;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * 每个Advisor必须包含Pointcut和advice
 *
 *
 * @Classname CustomAdvisor1
 * @Description TODO
 * @Author Jack
 * Date 2021/1/13 15:07
 * Version 1.0
 */
@Component
@Order(1)
public class CustomAdvisor1 implements PointcutAdvisor {

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
}
