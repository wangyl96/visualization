package com.sinosoft.visualization.user.response;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * ResponseResult
 * 接口返回结果注解
 * @author Dongx
 * Description:
 * Created in: 2019年5月6日14:11:08
 * Modified by:
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ResponseResult {
    Class<? extends Result> value() default ServerResponse.class;
}
