package com.sinosoft.datasupplyplatform.user.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 序列化，就是为了在不同时间或不同平台的JVM之间共享实例对象
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "sys_menu")
@NoArgsConstructor
public class SysMenu implements Serializable {

    /**
     * serialVersionUID 用来表明类的不同版本间的兼容性
     */
    private static final long serialVersionUID = 32943964482374243L;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    @GeneratedValue(generator = "PrimaryKeyGenerator")
    @GenericGenerator(name = "PrimaryKeyGenerator", strategy = "uuid")
    private String id;

    @Basic
    @Column(name = "parent_id", nullable = true, length = 32)
    private String parentId;

    @Basic
    @Column(name = "menu_name", nullable = true, length = 128)
    private String menuName;

    @Basic
    @Column(name = "menu_permission", nullable = true, length = 128)
    private String menuPermission;

    @Basic
    @Column(name = "menu_url", nullable = true, length = 256)
    private String menuUrl;

    @Basic
    @Column(name = "menu_icon", nullable = true, length = 16)
    private String menuIcon;

    @Basic
    @Column(name = "menu_sort", nullable = true, length = 11)
    private Integer menuSort;

    @Basic
    @Column(name = "menu_component", nullable = true, length = 256)
    private String menuComponent;

    @Basic
    @Column(name = "menu_path", nullable = true, length = 256)
    private String menuPath;

    @Basic
    @Column(name = "redirect", nullable = true, length = 256)
    private String redirect;

    @Basic
    @Column(name = "creat_date", nullable = true, length = 256)
    private String creatDate;

    @Basic
    @Column(name = "founder", nullable = true, length = 256)
    private String founder;

    @Basic
    @Column(name = "modify_date", nullable = true, length = 256)
    private String modifyDate;

    @Basic
    @Column(name = "modify_founder", nullable = true, length = 256)
    private String modifyFounder;

    @Basic
    @Column(name = "active", nullable = true, length = 11)
    private long active;
}