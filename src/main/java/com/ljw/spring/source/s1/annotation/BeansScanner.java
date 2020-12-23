package com.ljw.spring.source.s1.annotation;


import com.ljw.spring.source.s1.scanner.AllClassScanner;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(AllClassScanner.class)
public @interface BeansScanner {

    String[] value() default {};

    String[] basePackages() default {};

    /**
     * 自定义注解扫描过滤类
     * 比如这个是@Beidou,那么将自动过滤所有使用自定义注解的@Beidou的类
     *
     * @return
     */
    Class<? extends Annotation> annotationClass() default Annotation.class;

}
