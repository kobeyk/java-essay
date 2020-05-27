package com.appleyk.aop;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class AopMainTest {

    public static void main(String[] args) throws Exception{
        AbstractApplicationContext context =new ClassPathXmlApplicationContext("aop.xml");
        ProjectService bean = context.getBean(ProjectService.class);
        bean.save(1000L,"数据智造平台");
        System.out.println("========== 下面是抛异常的调用 ===========");
        bean.save(null,"数据智造平台");
    }
}
