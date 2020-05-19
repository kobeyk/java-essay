package com.appleyk.starter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/17 11:31 AM
 */
public class MyStarterInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 获取父容器的配置（原Spring.xml中的配置信息）
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * 获取子容器的配置（原SpringMVC.xml中的配置信息）
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}
