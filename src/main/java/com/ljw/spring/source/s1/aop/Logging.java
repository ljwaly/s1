package com.ljw.spring.source.s1.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.stereotype.Component;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

@Component
@Aspect
public class Logging {
    /**
     * 将Pointcut和advice组合起来，
     * 封装成一个对象，就是advisor（一个切面）
     *
     * 当spring扫描到@Aspect
     * 就会去扫描Pointcut和@Around，就会封装成Advisor
     */


    /**
     * 这个是切点Pointcut
     */
    @Pointcut("execution(public * com.ljw.spring.source.s1.service.*.*(..))")
//    @Pointcut("within(com.ljw.spring.source.s1.service..*) ")
    public void pc1() {
    }


    /**
     * 这个就是advice
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pc1()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        /**
         * 针对@Aspect注解的切面，添加额外一个默认的切面，
         * 可以放入一个MethodInvocation
         */
        MethodInvocation methodInvocation = ExposeInvocationInterceptor.currentInvocation();

        /**
         * 可以获取参数
         */
        Method method = methodInvocation.getMethod();
        Object[] arguments = methodInvocation.getArguments();
        AccessibleObject staticPart = methodInvocation.getStaticPart();





        System.out.println("------around-before");

        Object result = joinPoint.proceed();

        System.out.println("------around-after");


        return result;
    }
}
