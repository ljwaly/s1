package com.ljw.spring.source.s1.scanner.selector;

import com.ljw.spring.source.s1.annotation.ImportSelectorBeansScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImportSelectorScanner implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);

        boolean acceptAllBeans = true;

        try {

            /**
             * 获取有自定义扫描注解的类
             */
            MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(SelfDefineImportSelectorScannerTest.class.getName());
            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

            /**
             * 从注解上拿到自定义的注解信息
             */
            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(ImportSelectorBeansScanner.class.getName());

            /**
             * 拿到具体的注解内部的字段信息
             */
            AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(annotationAttributes);


            ImportSelectorBeanPackageScanner scanner = new ImportSelectorBeanPackageScanner(registry);


            //扫描特定的annotationClass的注解,如果设置了定义的注解，则只过滤这个注解，目前没有做实现
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
                        (metadataReader1, metadataReaderFactory) ->{
                            return true;
                        }
                );
            }

            scanner.doScan(StringUtils.toStringArray(basePackages));




        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }



    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
