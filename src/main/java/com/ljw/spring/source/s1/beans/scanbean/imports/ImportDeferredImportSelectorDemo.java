package com.ljw.spring.source.s1.beans.scanbean.imports;

import com.ljw.spring.source.s1.beans.scanbean.imports.vo.HN;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.JS;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.LN;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.SH;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;


/**
 * 模拟springboot调用原理
 *
 * 实现了DeferredImportSelector接口的类ImportSelectorDemo，本身并不会实例化
 */
public class ImportDeferredImportSelectorDemo implements DeferredImportSelector {

    /**
     * 被内部类的group进行回调
     *
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        //第四个被调用
        System.out.println("----------ImportDeferredImportSelectorDemo.selectImports");

        /**
         * 返回需要实例化的类的全类名列表
         */
        return new String[]{
                HN.class.getName(),
                LN.class.getName(),
                JS.class.getName(),
                SH.class.getName()
        };
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

        //第一个被调用
        System.out.println("----------ImportDeferredImportSelectorDemo.getImportGroup");
        return ImportDeferredImportSelectorGroupDemo.class;
    }




    private static class ImportDeferredImportSelectorGroupDemo implements DeferredImportSelector.Group {

        List<Entry> list = new ArrayList<>();

        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {

            //第二个被调用
            System.out.println("----------ImportDeferredImportSelectorGroupDemo.process");

            /**
             * 搜集需要实例化的类
             * 调用这个外部类的接口selectImports()实现方法
             */
            String[] strings = selector.selectImports(metadata);
            for (String string: strings) {
                list.add(new Entry(metadata, string));
            }

        }

        @Override
        public Iterable<Entry> selectImports() {

            //第三个被调用
            System.out.println("----------ImportDeferredImportSelectorGroupDemo.selectImports");
            return list;
        }
    }
}
