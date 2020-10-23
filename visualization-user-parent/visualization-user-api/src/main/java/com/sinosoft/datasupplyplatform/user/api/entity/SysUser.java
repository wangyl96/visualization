package com.sinosoft.datasupplyplatform.user.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "sys_user")
@NoArgsConstructor
public class SysUser implements Serializable {

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
    @Column(name = "user_name", nullable = true, length = 32)
    private String userName;

    @Basic
    @Column(name = "user_phone", nullable = true, length = 16)
    private String userPhone;

    @Basic
    @Column(name = "user_mail", nullable = true, length = 128)
    private String userMail;

    @Basic
    @Column(name = "user_company", nullable = true, length = 32)
    private String userCompany;

    @Basic
    @Column(name = "creat_date", nullable = true, length = 64)
    private String creatDate;

    @Basic
    @Column(name = "founder", nullable = true, length = 16)
    private String founder;

    @Basic
    @Column(name = "modify_date", nullable = true, length = 64)
    private String modifyDate;

    @Basic
    @Column(name = "modify_founder", nullable = true, length = 64)
    private String modifyFounder;

    @Basic
    @Column(name = "user_login_time", nullable = true, length = 64)
    private String userLoginTime;

    @Basic
    @Column(name = "user_login_ip", nullable = true, length = 64)
    private String userLoginIp;

    @Basic
    @Column(name = "avatar", nullable = true, length = 64)
    private String avatar;

    @Basic
    @Column(name = "user_login_key", nullable = true, length = 64)
    private String userLoginKey;

    @Basic
    @Column(name = "active", nullable = true, length = 11)
    private long active;


}
