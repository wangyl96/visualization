package com.sinosoft.datasupplyplatform.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理测试接口
 * @author wangyl
 */
@Api("用户管理测试接口-HELLO WORLD")
@RestController
public class HelloController {

    @GetMapping("/hello")
    @ApiOperation(value = "用户管理-HELLO WORLD", notes = "用户管理-HELLO WORLD")
    public String hello() {
        return "Hello World.";
    }

}
