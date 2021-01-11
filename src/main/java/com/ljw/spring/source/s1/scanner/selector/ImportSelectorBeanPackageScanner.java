package com.ljw.spring.source.s1.scanner.selector;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

public class ImportSelectorBeanPackageScanner extends ClassPathBeanDefinitionScanner {
    /**
     * 这里主要是为了全包扫描，不使用默认的过滤器
     *
     * @param registry
     */
    public ImportSelectorBeanPackageScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }
}
