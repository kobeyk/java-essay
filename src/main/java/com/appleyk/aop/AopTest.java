package com.appleyk.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
@Component
@Aspect
public class AopTest {

    @Pointcut("execution(* com.appleyk.aop.*.exe*(..))")
    public void exePointcut(){}

    @Before("exePointcut()")
    public void before(){
        System.out.println("方法执行前，要先调用我哦！");
    }

    @After("exePointcut()")
    public void after(){
        System.out.println("方法执行后，要先调用我哦！");
    }

    @AfterReturning("exePointcut()")
    public void returning(){
        System.out.println("方法执行完成后当结果成功返回后！调用我！");

    }

    @Around("exePointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("执行前");
        Object proceed = joinPoint.proceed();
        System.out.println("执行后");
        return proceed;
    }

}
