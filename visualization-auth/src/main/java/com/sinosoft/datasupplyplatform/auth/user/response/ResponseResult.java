package com.sinosoft.datasupplyplatform.auth.user.response;

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
public @interface ResponseResult {
    Class<? extends Result> value() default ServerResponse.class;
}
