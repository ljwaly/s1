package com.ljw.spring.source.s1.scanner;


import com.ljw.spring.source.s1.annotation.BeansScanner;
import org.springframework.context.annotation.Configuration;


/**
 * 自定义注解 + @Import导入
 * 一个jar包的所有的类
 */
@Configuration
@BeansScanner(basePackages = {"com.ljw.s2.bean"})
public class SelfDefineScanner {
}
