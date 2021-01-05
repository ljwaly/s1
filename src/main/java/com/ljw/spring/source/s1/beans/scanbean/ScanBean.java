package com.ljw.spring.source.s1.beans.scanbean;


import com.ljw.spring.source.s1.beans.scanbean.imports.ImportDemo;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportLjwBeanDefinitionRegistrar;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportLjwWithNothing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
//@ComponentScans({@ComponentScan("1"), @ComponentScan("2")})
@Component
@ComponentScan("com.ljw.spring.source.s1")
@Import({ImportDemo.class, ImportLjwBeanDefinitionRegistrar.class, ImportLjwWithNothing.class})
//@EnableAspectJAutoProxy
public class ScanBean {
}
