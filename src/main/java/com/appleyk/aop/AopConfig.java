package com.appleyk.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.appleyk.aop")
public class AopConfig {

    @Bean
    public SqlExecute sqlExecute(){
        return new SqlExecute();
    }
}
