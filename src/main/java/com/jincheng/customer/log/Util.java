package com.jincheng.customer.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class Util {
    @Pointcut("execution(* com.jincheng.customer.contoller.*.*(..))")//告诉spring切入哪个类的哪个方法
    public void myPointcut() {

    }

    //前置通知  在核心方法前面实行   9s
//    @Before("myPointcut()")
//    public void before(JoinPoint joinPoint) {
//        System.out.println("方法名称为:" + joinPoint.getSignature().getName() + "参数为:" + Arrays.toString(joinPoint.getArgs()));
//    }

    //@AfterReturning 有返回值
//    @After("myPointcut()")
//    @AfterReturning(value = "myPointcut()", returning = "returnValue")
//    public void after(JoinPoint joinPoint, Object returnValue) {
//        System.out.println(joinPoint.getSignature().getName() + "结果为" + returnValue);
//    }

    //异常通知
    @AfterThrowing(value = "myPointcut()", throwing = "e")
    public void err(JoinPoint joinPoint, Exception e) {
        System.out.println("method" + joinPoint.getSignature().getName() + "exception:" + e);
    }

    @Around(value = "myPointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            Object[] args = pjp.getArgs();//得到方法执行所需的参数
            System.out.println("around方法开始记录日志了。。。前置");
            rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）
            System.out.println("around方法开始记录日志了。。。后置");
            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("around方法开始记录日志了。。。异常");
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }

    }
}
