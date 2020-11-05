package com.sinosoft.datasupplyplatform.user.api.feign;

import com.sinosoft.datasupplyplatform.user.api.dto.Oauth2TokenDto;
import com.sinosoft.visualization.common.basic.constants.ServiceConstant;
import com.sinosoft.visualization.common.feign.config.CustomFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务
 * @author wangyl
 * @describe FeignClient需要指定configuration为CustomFeignConfig，用于服务间调用携带token
 * @creat
 * @modify
 */
@Component
@FeignClient(value = ServiceConstant.USER_SERVICE, configuration = CustomFeignConfig.class)
public interface UserServiceClient {

    @PostMapping("/oauth/token")
    Oauth2TokenDto postAccessToken();
}
