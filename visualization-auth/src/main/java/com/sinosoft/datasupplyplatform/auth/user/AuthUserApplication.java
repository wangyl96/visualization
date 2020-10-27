package com.sinosoft.datasupplyplatform.auth.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * AuthUserApplication
 *
 * @author wangyl
 * Description:
 * Created in: 2020/8/24
 * Modified by:
 */
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class AuthUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthUserApplication.class, args);
    }
}
