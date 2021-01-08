package com.ljw.spring.source.s1.beans.scanbean.jconditional;

import com.ljw.spring.source.s1.beans.scanbean.imports.vo.AH;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class OnBeanCondition implements Condition {

    /**
     * 根据规则匹配成功，
     * 则返回true
     * 不符合规则的，返回false
     *
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            if (metadata.isAnnotated(ConditionOnBean.class.getName())) {
                MergedAnnotations annotations = metadata.getAnnotations();

                MergedAnnotation<ConditionOnBean> conditionOnBeanMergedAnnotation = annotations.get(ConditionOnBean.class);
                Optional<Object> value = conditionOnBeanMergedAnnotation.getValue("value");
                Class<?>[] classes = (Class<?>[]) value.get();

                Class<?> aClass = ClassUtils.forName(classes[0].getName(), ClassUtils.getDefaultClassLoader());

                String[] beanNamesForType = context.getBeanFactory().getBeanNamesForType(aClass, true, false);


                if (!StringUtils.isEmpty(beanNamesForType)) {
                    System.out.println("beanNamesForType.length = " + beanNamesForType.length);
                    if (beanNamesForType.length > 0){
                        return true;
                    }
                }

                String[] beanDefinitionNames = context.getRegistry().getBeanDefinitionNames();




                //.class类型转string
//                MultiValueMap<String, Object> attributes = annotations.stream(ConditionOnBean.class)
//                        .filter(MergedAnnotationPredicates.unique(MergedAnnotation::getMetaTypes))
//                        .collect(MergedAnnotationCollectors.toMultiValueMap(MergedAnnotation.Adapt.CLASS_TO_STRING));

//                String[] values = context
//                        .getBeanFactory()
//                        .getBeanNamesForType(
//                                ClassUtils.forName(((String[]) attributes.get("value").get(0))[0] + "", ClassUtils.getDefaultClassLoader()),
//                                true,
//                                true);
//                if (!StringUtils.isEmpty(values) && values.length > 0) {
//                    System.out.println("values = " + values);
//                    return  true;
//                }




            }


        } catch (Exception e) {
            e.printStackTrace();
        }



        return false;
    }
}
