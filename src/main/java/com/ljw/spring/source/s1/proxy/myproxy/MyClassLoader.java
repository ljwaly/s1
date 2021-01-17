package com.ljw.spring.source.s1.proxy.myproxy;

import java.io.File;

public class MyClassLoader extends ClassLoader{
    private File file;

    public MyClassLoader(String path) {
        this.file = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
