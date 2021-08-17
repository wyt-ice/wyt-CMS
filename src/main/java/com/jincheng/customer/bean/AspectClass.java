package com.jincheng.customer.bean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class AspectClass {
    private static final Logger logger = LoggerFactory.getLogger(AspectClass.class);

    @Pointcut("execution(* com.jincheng.customer.contoller.*.*(..))")
    public void pointCut() {

    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        logger.info("调用方法名：" + joinPoint.getSignature().getName());
        System.out.println("调用方法名：" + joinPoint.getSignature().getName());
        System.out.println("参数：" + Arrays.toString(joinPoint.getArgs()));
    }
}
