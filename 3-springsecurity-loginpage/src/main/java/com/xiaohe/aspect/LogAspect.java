package com.xiaohe.aspect;

import com.alibaba.fastjson2.JSON;
import com.xiaohe.annotation.AOPLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-08 22:28
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    // 切入点是所有加了 AOPLog 的方法。
    @Pointcut("@annotation(com.xiaohe.annotation.AOPLog)")
    public void pointcut() {}

    // @Around() 的参数是什么样的切入点
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 如果方法执行时期出现异常，我们希望它被全局异常处理捕获到，而不是在这里catch，所以把异常抛出。
        Object proceed = null;
        try {
            before(joinPoint);
            proceed = joinPoint.proceed();
            after(joinPoint);

        } finally {
            log.info("---------------------end---------------------" + System.lineSeparator());
        }
        return proceed;
    }
    private void before(ProceedingJoinPoint joinPoint) {
        // 获取request，才能获取ip、uri...
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取注解
        AOPLog aopLog = getAOPLog(joinPoint);

        // 打印日志
        log.info("--------------------start--------------------");
        log.info("URL:          {}",  request.getRequestURL());
        log.info("方法作用:      {}", aopLog.comment());
        log.info("Http Method:  {}", request.getMethod());
        log.info("方法全路径:     {}.{}", joinPoint.getSignature().getDeclaringTypeName(), ((MethodSignature)joinPoint.getSignature()).getName());
        log.info("请求IP地址:    {}", request.getRemoteHost());
        log.info("入参:          {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private void after(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("返回值: {}", JSON.toJSONString(joinPoint.proceed()));
    }

    /**
     * 获取注解
     * @param joinPoint
     * @return
     */
    private AOPLog getAOPLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(AOPLog.class);
    }
}
