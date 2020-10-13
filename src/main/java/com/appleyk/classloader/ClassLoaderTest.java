package com.appleyk.classloader;

import java.io.InputStream;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:26 下午 2020/9/15
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null){
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name,bytes,0, bytes.length);
                }catch (Exception e){
                    throw new ClassNotFoundException();
                }
            }
        };

        ////obj对象确实是这个类的实例，但是类加载器却使用的是自定义的
        //Object obj = myClassLoader.loadClass("com.appleyk.classloader.ClassLoaderTest");
        //System.out.println(obj);
        //// 但是，虚拟机里面，也有一个自己的类加载器加载的ClassLoaderTest
        //// 所以比较的时候，显然为false
        //System.out.println(obj instanceof ClassLoaderTest);
    }
}
