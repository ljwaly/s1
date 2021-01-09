package com.ljw.spring.source.s1.beans.scanbean.jconditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Optional;

public class OnPropertyCondition implements Condition {

    /**
     * 根据规则匹配成功，
     * 则返回true,才会注入自定义condition注解的类
     * 不符合规则的，返回false
     *
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        MergedAnnotations annotations = metadata.getAnnotations();
        MergedAnnotation<ConditionOnProperty> conditionOnPropertyMergedAnnotation = annotations.get(ConditionOnProperty.class);
        Optional<Object> value = conditionOnPropertyMergedAnnotation.getValue("value");
        Object o = value.get();

        if (o instanceof String[]) {
            String[] params = (String[]) o;
            Environment environment = context.getEnvironment();
            String property = environment.getProperty(params[0]);
            if ("1".equals(property)) {
                return true;
            }
        }

        return false;
    }
}
