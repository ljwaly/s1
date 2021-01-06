package com.ljw.spring.source.s1.beans.scanbean.imports;


import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Optional;

public class ImportDemo implements ImportSelector {


    /**
     * 调用@Import注解方法时候，会执行这个方法
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MergedAnnotations annotations = importingClassMetadata.getAnnotations();
        MergedAnnotation<EnableAspectJAutoProxy> ma = annotations.get(EnableAspectJAutoProxy.class);

        if (ma != null){
            Optional<Object> proxyTargetClass = ma.getValue("proxyTargetClass");
//            Object proxyTargetClassValue = proxyTargetClass.get();
            /**
             * EnableAspectJAutoProxy注解是在扫描类的上面的
             *
             * 这里可以拿到EnableAspectJAutoProxy注解的proxyTargetClass的值
             */
        }


        /**
         * 返回类的完整限定名
         */
        return new String[]{ ImportLjwBean.class.getName() };
    }
}
