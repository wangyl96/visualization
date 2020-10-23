package com.sinosoft.datasupplyplatform.user.service.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.sinosoft.datasupplyplatform.user.api.dto.MenuDto;
import com.sinosoft.datasupplyplatform.user.api.entity.SysMenu;
import com.sinosoft.datasupplyplatform.user.repository.SysMenuRepository;
import com.sinosoft.datasupplyplatform.user.service.MenuService;
import com.sinosoft.visualization.common.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author wangyl
 * @describe
 * @creat
 * @modify
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuRepository sysMenuRepository;

    /**
     * 获取菜单列表
     * @return
     */
    @Override
    public List<MenuDto> findUserMenu() {
        List<MenuDto> menuDtoList = new ArrayList<>();
        List<SysMenu> userMenus;
        // 查询全部的菜单
        List<SysMenu> allMenus = sysMenuRepository.findAll();
        if (allMenus.size() > 0) {
            allMenus.stream()
                    // dto封装
                    .map(MenuDto::new)
                    // 去重
                    .distinct().forEach(menuDtoList::add);
            // 排序、构建树形关系
            return TreeUtil.buildTree(CollUtil.sort(menuDtoList, Comparator.comparingInt(MenuDto::getSort)),
                    "-1");
        }
        return Lists.newArrayList();
    }
}
