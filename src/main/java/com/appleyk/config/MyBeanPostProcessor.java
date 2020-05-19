package com.appleyk.config;

import com.appleyk.bean.HouseBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * <p>自定义Bean后处理器类，这个处理器会对所有IOC容器中的bean进行初始化前和后的处理</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 11:18 PM
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    // 由于BeanPostProcessor接口内置了两个默认的方法，所以，实现这个接口，不实现接口方法也是ok的


    /**
     * 在bean初始化之前调用
     * @param bean bean对象
     * @param beanName bean对象的名称
     * @return bean对象（就是在bean初始化之前，干一些事情，干完，bean从哪来的就从哪回去）
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 这里我们主要找bean是houseBean的
        if(bean instanceof HouseBean){
            System.err.println("Hey（postProcessBeforeInitialization），买房前，要先签购房合同哦！");
        }
        return bean;
    }

    /**
     * 在bean初始化之后调用
     * @param bean bean对象
     * @param beanName bean对象的名称
     * @return bean对象（就是在bean初始化之前，干一些事情，干完，bean从哪来的就从哪回去）
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 这里我们主要找bean是houseBean的
        if(bean instanceof HouseBean){
            System.err.println("Hey（postProcessAfterInitialization），买房后，要趁早计划装修哦！");
        }
        return bean;
    }
}
