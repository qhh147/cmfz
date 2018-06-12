package com.baizhi.aspect;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao1.LogDAO;
import com.baizhi.entity.Log;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/5.
 */
public class Around implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        String Aname = (String) session.getAttribute("Aname");//username
        Method method = methodInvocation.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);

        String things = annotation.name();//things
        Date date = new Date();//dodate
        String flag="";//result
        Object proceed =null;
        try {
            proceed = methodInvocation.proceed();
            flag="成功";
        }catch (Exception e){
            flag="失败";
        }
        //添加日志
        ApplicationContext apc=new ClassPathXmlApplicationContext("/spring.xml");
        LogDAO bean = (LogDAO) apc.getBean("logDAO");
        Log log = new Log(null,Aname,date,things,flag);
        bean.insert(log);
        return proceed;
    }
}
