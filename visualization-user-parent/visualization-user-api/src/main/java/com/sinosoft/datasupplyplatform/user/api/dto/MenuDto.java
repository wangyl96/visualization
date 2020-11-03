package com.sinosoft.datasupplyplatform.user.api.dto;

import com.sinosoft.visualization.common.basic.entity.TreeEntity;
import com.sinosoft.datasupplyplatform.user.api.entity.SysMenu;
import lombok.Data;

/**
 * @author wangyl
 * @describe
 * @creat
 * @modify
 */
@Data
public class MenuDto extends TreeEntity<MenuDto> {

//    /**
//     * 父菜单ID
//     */
//    private String parentId;
//
//    private String icon;
//
//    private String name;
//
//    private String url;
//
//    private String redirect;
//
//    private boolean spread = false;
//
//    private String path;
//
//    private String component;
//
//    private String code;
//
//    private String label;
//
//    private String[] roles;
//
//    private String remark;
//
//    public MenuDto(SysMenu menu) {
//        this.id = menu.getId();
//        this.parentId = menu.getParentId();
//        this.icon = menu.getMenuIcon();
//        this.name = menu.getMenuName();
//        this.url = menu.getMenuUrl();
//        this.sort = menu.getMenuSort();
//        this.component = menu.getMenuComponent();
//        this.path = menu.getMenuPath();
//        this.redirect = menu.getRedirect();
//    }
//
//    @Override
//    public String getCode() {
//        return code;
//    }
//
//    @Override
//    public void setCode(String code) {
//        this.code = code;
//    }
}
