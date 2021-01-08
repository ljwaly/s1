package com.ljw.spring.source.s1.beans.scanbean.jconditional;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;
import java.util.Map;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(value = OnPropertyCondition.class)
public @interface ConditionOnProperty {

    String[] value() default {};
    String[] name() default {};

}
