package com.sinosoft.datasupplyplatform.auth.user.controller;

import com.sinosoft.datasupplyplatform.auth.user.domain.Oauth2TokenDto;
import com.sinosoft.visualization.common.basic.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 * @author wangyl
 */
@Slf4j
@Api("Oauth2获取令牌")
@ResponseResult
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * Oauth2登录认证
     */
//    @Log("Oauth2登录认证")
    @ApiOperation(value = "Oauth2获取令牌", notes = "Oauth2登录认证")
    @RequestMapping(value = "oauth/token", method = RequestMethod.POST)
    public Oauth2TokenDto postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        log.info("----------------------------------获取认证令牌开始-----------------------------");
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();
        log.info("----------------------------------获取认证令牌结束-----------------------------");
        return oauth2TokenDto;
    }
}
