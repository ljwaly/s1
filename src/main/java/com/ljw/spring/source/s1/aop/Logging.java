package com.ljw.spring.source.s1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logging {


//    @Pointcut("within(com.ljw.spring.source.s1.service..*) ")
    @Pointcut("execution()")
    public void remoteCallPointcut() {
    }


    @Around("remoteCallPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("------around-before");

        Object result = joinPoint.proceed();

        System.out.println("------around-after");


        return result;
    }
}
