package com.ljw.spring.source.s1.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 自定义scope需要我们自己管理对象
 * 针对对象创建的处理
 * 类似：单例模式（创建bean前后自己有一套前后置处理流程），多例模式等，这个是Scope模式
 *
 * 这个是一种我们创建Bean对象之后，对bean对象的一些操作的规范
 *
 * 就是创建bean前后，我们可以做哪些需要额外处理的操作
 * 每一个CustomScope就是一种定义的操作规范
 *
 */
public class CustomScope implements Scope {

    private ThreadLocal local = new ThreadLocal();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        if (local.get() != null) {
            return local.get();
        }

        //调用接口方法，通过回调创建实例
        Object object = objectFactory.getObject();
        local.set(object);
        return object;
    }

    @Override
    public Object remove(String name) {
        Object object = local.get();
        local.remove();
        return object;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
