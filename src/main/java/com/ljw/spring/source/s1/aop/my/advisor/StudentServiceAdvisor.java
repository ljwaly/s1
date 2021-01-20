package com.ljw.spring.source.s1.aop.my.advisor;

import com.ljw.spring.source.s1.aop.my.advice.StudentServiceAdvice;
import com.ljw.spring.source.s1.aop.my.pointcut.StudentServicePointCut;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname StudentServiceAdvisor
 * @Description TODO
 * @Author Jack
 * Date 2021/1/19 14:47
 * Version 1.0
 */
@Component
public class StudentServiceAdvisor implements PointcutAdvisor {

    @Autowired
    private StudentServicePointCut studentServicePointCut;

    @Autowired
    private StudentServiceAdvice studentServiceAdvice;

    @Override
    public Pointcut getPointcut() {
        return studentServicePointCut;
    }

    @Override
    public Advice getAdvice() {
        return studentServiceAdvice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
