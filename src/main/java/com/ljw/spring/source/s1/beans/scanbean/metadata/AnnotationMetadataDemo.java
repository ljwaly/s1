package com.ljw.spring.source.s1.beans.scanbean.metadata;

import com.ljw.spring.source.s1.beans.MetadataDemoVo;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;


@Component
public class AnnotationMetadataDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void metaData() {

        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);

        try {
            /**
             * 如果要获取一个类的Metadata对象信息
             * 传入类的全类名
             *
             * 可以获取到所有的类的属性信息（类名，属性，方法，注解）
             */
            MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(MetadataDemoVo.class.getName());
            System.out.println("metadataReader = " + metadataReader);

            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
            MergedAnnotations annotations = annotationMetadata.getAnnotations();
            System.out.println("annotations = " + annotations);


            /**
             * 获取类的注解信息
             */
            MergedAnnotation<Component> componentMergedAnnotation = annotations.get(Component.class);
            System.out.println("componentMergedAnnotation = " + componentMergedAnnotation);

            AnnotationAttributes annotationAttributes = componentMergedAnnotation.asAnnotationAttributes();
            System.out.println("annotationAttributes = " + annotationAttributes);




        } catch (Exception e) {
            e.printStackTrace();
        }




    }


}
