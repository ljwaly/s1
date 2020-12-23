package com.ljw.spring.source.s1.scanner;

import com.ljw.spring.source.s1.annotation.BeansScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class AllClassScanner implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean acceptAllBeans = true;

        AnnotationAttributes annoAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(BeansScanner.class.getName()));
        BeanPackageScanner scanner = new BeanPackageScanner(registry);


        //扫描特定的annotationClass的注解,如果设置了定义的注解，则只过滤这个注解
        Class<? extends Annotation> annotationClass = annoAttrs.getClass("annotationClass");
        if (!Annotation.class.equals(annotationClass)){
            acceptAllBeans = false;
            scanner.addIncludeFilter(new AnnotationTypeFilter(annotationClass));
        }


        List<String> basePackages = new ArrayList<>();

        for (String pkg : annoAttrs.getStringArray("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }

        for (String pkg : annoAttrs.getStringArray("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }


        if (acceptAllBeans) {
            //增加一个适配方法，默认全部都获取
            //在这里重写match方法
            scanner.addIncludeFilter(
                    (metadataReader, metadataReaderFactory) ->{
                        return true;
                    }
            );
        }

        scanner.doScan(StringUtils.toStringArray(basePackages));

    }
}
