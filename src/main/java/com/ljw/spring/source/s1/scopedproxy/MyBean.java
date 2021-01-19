package com.ljw.spring.source.s1.scopedproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 启动时候，这个ScopedProxyBean已经注入了
 *
 * 只是注入的并不是对象本身
 *
 *
 */
@Component
public class MyBean {
    @Autowired
    private ScopedProxyBean scopedProxyBean;

    public void test () {
        scopedProxyBean.code();
    }

}
