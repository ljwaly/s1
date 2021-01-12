package com.ljw.spring.source.s1.scanner.selector;


import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class SelfDefineImportSelectorScanner implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { ImportSelectorScanner.class.getName()};
    }
}
