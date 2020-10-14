package com.appleyk.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>JVM 双亲委派模式：
 * 1、除Bootstrap ClassLoader（顶层类加载器）没有父类加载器外，其他类加载器均有父
 *    其他（从上到下依次是：
 *    启动类加载器、扩展类加载器、应用程序类加载器和我们自定义的类加载器（继承父类ClassLoader即可实现）
 *    ）
 * 2、子类加载器收到加载类的请求时，会优先委派给其父类加载器进行加载，如果父类没有，再向下交给子类来加载
 * 3、如果最终连子类都没有找到类的话，那就gg了，直接抛出ClassNotFoundException
 * </p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:54 下午 2020/9/15
 */
public class Object {
    static{
        System.out.println("Object init !");
    }
    private static String name = "object";
    public static void main(String[] args) throws Exception{
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                    InputStream is = getClass().getResourceAsStream(name.substring(name.lastIndexOf(".")+1)+".class");
                    if(is == null){
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e){
                    throw new ClassNotFoundException();
                }
            }
        };
            System.out.println(loader.loadClass("com.appleyk.classloader.Object"));
    }
}





