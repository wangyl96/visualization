package com.sinosoft.datasupplyplatform.auth.user.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面
 * @author Dongx
 */
@Slf4j
@Aspect
@Component
public class WebLogAspectConfig {

    @Pointcut("execution(public * com.sinosoft..controller..*.*(..))")
    public void webLog() {}

    @Pointcut("execution(public * com.sinosoft..service..*.*(..))")
    public void serviceLog() {}


    @Before("webLog()")
    public void doBeforeOfWeb(JoinPoint joinPoint) {
        log.info("Web-REQUEST_ARGS: {}", joinPoint.getArgs());
    }

    @Before("serviceLog()")
    public void doBeforeOfService(JoinPoint joinPoint) {
        log.info("Service-REQUEST_ARGS: {}", joinPoint.getArgs());
    }
    
    @Around("webLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = null;
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        String requestMethod = joinPoint.getSignature().getDeclaringTypeName()+  "." + joinPoint.getSignature().getName();
        String requestArgs = Arrays.toString(joinPoint.getArgs());

        // 方法执行
        result = joinPoint.proceed();

        // 获取响应码 由于在AOP中永远在方法执行完毕后才执行 所以状态码永远是200
        //HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //int responseStatus = httpServletResponse.getStatus();

        String responseArgs = Arrays.toString(joinPoint.getArgs());
        long endTime = System.nanoTime();
        log.info("URL: {}, HTTP_METHOD: {}, REQUEST_IP: {}, REQUEST_METHOD: {}, REQUEST_ARGS: {}, RESPONSE_ARGS: {}, RESPONSE_TIME: {}ms",
                request.getRequestURL().toString(), request.getMethod(), request.getRemoteAddr(), requestMethod, requestArgs, result,
                (endTime - startTime) / 1000_000);
        return result;
    }
    
    @AfterReturning(pointcut = "serviceLog()", returning = "returnValue")
    public void doAfterReturningOfService(JoinPoint joinPoint, Object returnValue) {
        log.info("Service-RESPONSE_ARGS: {}", returnValue);
    }

    @AfterReturning(pointcut = "webLog()", returning = "returnValue")
    public void doAfterReturningOfWeb(JoinPoint joinPoint, Object returnValue) {
        log.info("Web-RESPONSE_ARGS: {}", returnValue);
        MDC.clear();
    }
}
