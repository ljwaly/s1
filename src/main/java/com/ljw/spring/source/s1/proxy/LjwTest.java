package com.ljw.spring.source.s1.proxy;

//import sun.misc.ProxyGenerator;
//
//import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class LjwTest {

    public static void main(String[] args) {
        ClassLoader loader = LjwTest.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { People.class};
        InvocationHandler handler = new Parent(new Xiaoming());

        People people = (People) Proxy.newProxyInstance(loader, interfaces, handler);
//        getProxyFile();
        people.findMM("beauty");
    }

    /**
     * 生成代理对象的文件
     */
//    public static void getProxyFile(){
//        byte[] $Proxy0s = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{People.class});
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.class");
//            fileOutputStream.write($Proxy0s);
//            fileOutputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
