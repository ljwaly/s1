package com.ljw.spring.source.s1.beandefinitionpostprocessor;

import com.ljw.spring.source.s1.beans.Student;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * FactoryBean模式创建对象
 *
 * 可以灵活自定义 需要我们自己创建的 实例
 * 在getObject()方法里面定义实例化过程
 */
@Component
public class FactoryBeanDemo implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        Student student = new Student();
        student.setName("ljwFactoryBeanDemo");
        return student;
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }
}
