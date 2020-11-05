package com.sinosoft.datasupplyplatform.auth.user.service;

import cn.hutool.core.collection.CollUtil;
import com.sinosoft.datasupplyplatform.auth.user.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源与角色匹配关系管理业务类
 * Created by macro on 2020/6/19.
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/auth/currentUser", CollUtil.toList("ADMIN", "TEST"));
        resourceRolesMap.put("/api/business/getTodayOverview", CollUtil.toList("ADMIN", "TEST"));
        resourceRolesMap.put("/api/user/login", CollUtil.toList("ADMIN", "TEST"));
        resourceRolesMap.put("/api/user/getInfo", CollUtil.toList("ADMIN", "TEST"));
        resourceRolesMap.put("/api/business/getTodayMapData", CollUtil.toList("ADMIN", "TEST"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
