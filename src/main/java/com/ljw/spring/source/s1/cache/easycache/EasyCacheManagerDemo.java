package com.ljw.spring.source.s1.cache.easycache;

import com.ljw.spring.source.s1.annotation.EasyCache;
import org.springframework.stereotype.Component;

@Component
public class EasyCacheManagerDemo {

    @EasyCache(key = "#key")
    public String get(String key){
        System.out.println("----进入实际方法：EasyCacheManagerDemo.get");
        return key + "EasyCacheDemo";
    }
}
