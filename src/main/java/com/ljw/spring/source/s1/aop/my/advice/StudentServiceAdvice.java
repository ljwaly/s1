package com.ljw.spring.source.s1.aop.my.advice;

import com.ljw.spring.source.s1.annotation.EasyCache;
import com.ljw.spring.source.s1.util.SPELParserUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StudentServiceAdvice implements MethodInterceptor {

    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        /**
         * 可以增加对使用@EasyCache的处理
         */

        Class<?> targetClass = invocation.getThis().getClass();
        Method method = invocation.getMethod();
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);


        /**
         * 拿到方法上的参数名称
         *
         */
        String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(mostSpecificMethod);
        Object[] arguments = invocation.getArguments();


        /**
         * 拿到注解
         */
        EasyCache easyCache = mostSpecificMethod.getAnnotation(EasyCache.class);
        String key = easyCache.key();


        String realKey = SPELParserUtil.getValue(key, parameterNames, arguments);

//        StringBuilder sb = new StringBuilder();
//        if (!StringUtils.isEmpty(key)) {
//            String[] split = key.split(",");
//            for (int i = 0; i< split.length; i ++) {
//                int index = Integer.parseInt(split[i]) - 1;
//                if (arguments.length <= index) {
//                    throw new Exception(targetClass.getName() + "_" + method.getName() + ": @EasyCache key index out of lenght");
//                }
//                sb.append(arguments[index] + ":");
//            }
//        }
//
//        String realKey = sb.toString();

        Object o = cache.get(realKey);
        if (o != null) {
            return o;
        }


        Object reasult = invocation.proceed();
        if (reasult != null) {
            cache.put(realKey, reasult);
        }

        return reasult;
    }
}
