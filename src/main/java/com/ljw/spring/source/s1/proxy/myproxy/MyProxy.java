package com.ljw.spring.source.s1.proxy.myproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

public class MyProxy {

    static String path = "";

    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
            throws IllegalArgumentException {

        // 1.创建一个java的字符串的类内容
        String javaStr = getJavaStr(interfaces);

        // 2.创建一个以javaStr为类内容的.java类文件
        createJavaFile(javaStr);

        // 3.把.java文件变异成.class文件
        compiler();



        try {
            // 4.把字节码.class文件加载到JVM内存,拿到类对象
            MyClassLoader myClassLoader = new MyClassLoader(path);
            Class<?> $Proxy0 = myClassLoader.findClass("$Proxy0");


            // 5.通过类对象拿到构造方法，进行反射，得到实例
            Constructor<?> constructor = $Proxy0.getConstructor(InvocationHandler.class);
            Object o = constructor.newInstance(h);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }


    /**
     * 运行时，编译java文件，形成.class
     *
     */
    private static void compiler() {
        try {
            JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(path + "\\$Proxy0.java");


            JavaCompiler.CompilationTask task = systemJavaCompiler.getTask(
                    null, standardFileManager,
                    null,
                    null,
                    null,
                    javaFileObjects);

            task.call();
            standardFileManager.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createJavaFile(String javaStr) {
    }

    private static String getJavaStr(Class<?>[] interfaces) {

        return "";
    }
}
