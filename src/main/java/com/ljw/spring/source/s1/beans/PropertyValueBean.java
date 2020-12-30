package com.ljw.spring.source.s1.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * 注解@PropertySource优先级
 * 高于BeanDefinitionRegistryPostProcessor接口实现处理
 */
@Component
@PropertySource(value = "classpath:/application.properties")
public class PropertyValueBean implements EnvironmentAware {


    private String password;


    /**
     * 从Environment中取
     * 从本地配置中获取
     */
    @Value("${property.aba}")
    private String aba;




    @Resource
    private TestBean1 t1;

    @Autowired
    private TestBean2 t2;

    public PropertyValueBean (){
        System.out.println("----PropertyValueBean");

    }

    @PostConstruct
    public void init(){
        System.out.println("----PropertyValueBean.init");
    }

    public String getAba() {
        return aba;
    }

    public void setAba(String aba) {
        this.aba = aba;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("aba=" + aba);
        System.out.println("${property.aba}=" + environment.getProperty("property.aba"));
    }
}
