package com.ljw.spring.source.s1.beans.scanbean;


import com.ljw.spring.source.s1.annotation.BeansScanner;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportDeferredImportSelectorDemo;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportSelectorDemo;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportBeanDefinitionRegistrarDemo;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportWithNothing;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
//@ComponentScans({@ComponentScan("1"), @ComponentScan("2")})
@Component
@ComponentScan("com.ljw.spring.source.s1")
@Import({ImportSelectorDemo.class, ImportBeanDefinitionRegistrarDemo.class, ImportWithNothing.class, ImportDeferredImportSelectorDemo.class})
@PropertySource({"classpath:ljwcondition.properties"})
@EnableAspectJAutoProxy
//@ImportResource("classpath:spring.xml")//基本上用不到，现在基本上都是基于注解
public class ScanBean {
}
