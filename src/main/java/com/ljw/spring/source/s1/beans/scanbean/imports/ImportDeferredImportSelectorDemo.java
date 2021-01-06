package com.ljw.spring.source.s1.beans.scanbean.imports;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class ImportDeferredImportSelectorDemo implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("----------ImportDeferredImportSelectorDemo.selectImports");

        return new String[0];
    }

    /**
     * 这里必须返回实现DeferredImportSelector.Group的类
     *
     * 如果这里不实现，则会构建spring内部的Group实现类
     *
     * @return
     */
    @Override
    public Class<? extends Group> getImportGroup() {
        System.out.println("----------ImportDeferredImportSelectorDemo.getImportGroup");
        return ImportDeferredImportSelectorGroupDemo.class;
    }




    private static class ImportDeferredImportSelectorGroupDemo implements DeferredImportSelector.Group {

        List<Entry> list = new ArrayList<>();

        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            System.out.println("----------ImportDeferredImportSelectorGroupDemo.process");

        }

        @Override
        public Iterable<Entry> selectImports() {
            System.out.println("----------ImportDeferredImportSelectorGroupDemo.selectImports");
            return list;
        }
    }
}
