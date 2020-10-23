package com.sinosoft.datasupplyplatform.auth.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * AuthUserApplication
 *
 * @author wangyl
 * Description:
 * Created in: 2020/8/24
 * Modified by:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AuthUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthUserApplication.class, args);
    }
}
