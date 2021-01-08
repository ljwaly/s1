package com.ljw.spring.source.s1.beans.scanbean.jconditional;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(value = OnBeanCondition.class)
public @interface ConditionOnBean {

    Class<?>[] value() default {};
    String[] name() default {};

}
