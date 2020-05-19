package com.appleyk.bean;

import com.appleyk.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>房子Bean，房子初始化前，先签合同，房子初始化后，就是装修了</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class HouseBean implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware,InitializingBean,DisposableBean {

    private String name;
    private float price;

    public HouseBean(){

    }

    /**
     * 实现BeanFactoryAware接口中的SetBeanFactory()方法
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("Hey(setBeanFactory),我拿到了Bean工厂哦，拿到后我就可以从工厂里面取bean了，比如我取的是："+
        beanFactory.getBean("userService",UserService.class));
    }

    /**
     * 实现BeanNameAware接口中的setBeanName()方法
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        System.err.println("Hey(setBeanName),这里设置一下bean的name哦（其实就是bean的id） = "+s);
    }

    /**
     * 实现InitializingBean接口中唯一的方法afterPropertiesSet()方法，在bean初始化的时候，调用
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.print("Hey(afterPropertiesSet),HouseBean初始化的时候，我要检查下name是不是空哦");
        if(this.name == null){
            throw new NullPointerException("==房子的名称为空！");
        }else{
            System.err.println("==你买的房子的户型名称是："+this.name);
        }
    }

    /**
     * 初始化bean的时候，定义自己的初始化方法
     */
    public void myInitBean(){
        System.err.print("Hey(myInitBean)，HouseBean初始化的时候，我要检查下price是不是符合预算哦");
        if(this.price > 1000000.0){
            System.err.println("==Oh my god,太贵了，买不起啊，撤了撤了！");
        }else{
            System.err.println("==Wa,可以，终于买的起房了，爱了爱了！");
        }
    }

    /**
     * 实现ApplicationContextAware接口中的setApplicationContext()方法，获取Spring应用上下文（IOC容器）
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println("Hey(setApplicationContext),我拿到了Context上下文哦，拿到后我就可以从IOC容器面取bean了，比如我取的是："+ applicationContext.getBean("userService",UserService.class));
    }

    /**
     * 实现DisposableBean接口中的destroy()方法，销毁bean
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.err.println("Hey(destroy),兄弟,再见了！");
    }

    /**
     * 自定义Bean销毁方法
     */
    public void myDestroyBean(){
        System.err.println("Hey(myDestroyBean)，兄弟，我多说一句，我会想你的，再见！");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
