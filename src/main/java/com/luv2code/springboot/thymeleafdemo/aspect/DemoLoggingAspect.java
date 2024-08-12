package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // setup Logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..   ))")
    public void forControllerPackage() {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..   ))")
    public void forServicePackage() {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..   ))")
    public void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()  ")
    public void forAppFlow() {}


    // add before advice

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        // method were call
        String methodName = joinPoint.getSignature().getName();
        myLogger.info("@Before calling methods the method name "+methodName);
        // argument
        Object[] args = joinPoint.getArgs();
        // loop
        for (Object arg : args) {
            myLogger.info("@Before calling args: " + arg);
        }
    }
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"

    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        // method were call
        String methodName = joinPoint.getSignature().getName();
        myLogger.info("@After calling methods the method name "+methodName);
        // argument
        Object[] args = joinPoint.getArgs();
        // loop
        for (Object arg : args) {
            myLogger.info("@After calling args: " + arg);
        }
    }



}
