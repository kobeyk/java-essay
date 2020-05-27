package com.appleyk.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.List;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class LogAspects {

    /**
     * 方法执行前，调用
     */
    public void before(JoinPoint joinPoint){
        /**
         *  joinPoint.getSignature() 拿到被增强的(连接点)方法签名，
         *  签名里面可以获取到方法所在的代理类和实际目标对象
         * joinpoint.getArgs();//方法的输入参数列表
         * joinpoint.getTarget().getClass().getName();//类全路径
         * joinpoint.getSignature().getDeclaringTypeName();//接口全路径
         * joinpoint.getSignature().getName();//调用的方法
         * joinpoint.getSignature().getClass();//调用的方法
         */
        System.out.println(joinPoint.getSignature().getDeclaringType()+"."+
                joinPoint.getSignature().getName()+"()方法执行前，" +
                "获取参数列表:"+ Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 方法执行后，调用(不管方法是否执行成功，这个都会调用)
     */
    public void after(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getDeclaringType()+"."+
                joinPoint.getSignature().getName()+"()方法执行后，" +
                "获取参数列表:"+ Arrays.asList(joinPoint.getArgs()));
    }

    /***
     * 环绕切入点，可以在控制方法是否执行，是否反复，且必须有返回值
     * @param joinPoint
     */
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = null ;
        System.out.println("环绕通知，方法执行前：先检查下方法的参数中是否包含'数据智造'");
        List<Object> params = Arrays.asList(joinPoint.getArgs());
        if(params.contains("数据智造平台")){
            System.out.println("环绕通知，方法执行前：方法参数满足执行条件，可以执行了！");
            // 如果包含，执行方法，如果方法这时候异常了，直接走异常的切点方法处理
            object = joinPoint.proceed();
            System.out.println("环绕通知，方法执行后：方法成功执行了！");
        }else{
            System.out.println("对不起，方法参数不满足条件，不能被成功执行！");
        }
        return object;
    }

    /**
     * 方法成功返回结果后，调用
     * @param result
     */
    public void afterReturn(Object result){
        System.out.println("结果："+result);
    }

    /**
     * 方法执行异常时，调用
     * @param ex
     */
    public void exception(Exception ex){
        System.out.println("方法执行异常信息（拿到后，记录日志）:"+ex.getMessage());
    }

}
