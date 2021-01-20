package com.ljw.spring.source.s1.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EasyCache {
    /**
     * 参数的位置，
     * 以逗号拆分
     * 比如第二个和第三个参数：key = "2,3"
     *
     * @return
     */
    String key() default "";

}
