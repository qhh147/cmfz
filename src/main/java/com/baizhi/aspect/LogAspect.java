package com.baizhi.aspect;

import com.baizhi.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.Servlet;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/6/11.
 */
@Configuration
@Aspect
public class LogAspect {
    @Pointcut(value = "execution(* com.baizhi.service.*.*(..))")
    public void cut(){

    }
    @Around(value = "cut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object[] args = proceedingJoinPoint.getArgs();//获取参数数组
        Object target = proceedingJoinPoint.getTarget();//获取代理类对象
        Object aThis = proceedingJoinPoint.getThis();//获取代理类对象
        String name = proceedingJoinPoint.getSignature().getName();//获取目标方法名
        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();//获取目标对象方法
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        String name1 = annotation.name();
        System.out.println(name1);

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String id = (String) requestAttributes.getRequest().getSession().getAttribute("id");
        Object proceed=null;
        try {
            proceed = proceedingJoinPoint.proceed();//执行目标方法
            System.out.println("执行结果为true");
        } catch (Throwable throwable) {
            System.out.println("执行结果为false");
            throwable.printStackTrace();
        }
        return proceed;
    }
}
