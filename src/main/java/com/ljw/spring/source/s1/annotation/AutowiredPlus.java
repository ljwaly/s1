package com.ljw.spring.source.s1.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

//@Autowired
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutowiredPlus {

    String value();

    String[] impls();

    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    boolean required() default true;


}
