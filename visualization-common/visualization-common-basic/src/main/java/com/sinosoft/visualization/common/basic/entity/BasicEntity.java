package com.sinosoft.visualization.common.basic.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * BasicEntity
 * 实体基类
 * @author wangyl
 * Description:
 * Created in: 2020/8/20
 * Modified by:
 */
@Data
@MappedSuperclass
@NoArgsConstructor
public class BasicEntity <T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "is_active", nullable = true)
    protected String is_active;

    @Basic
    @Column(name = "creator", nullable = true)
    private String creator;

    @Basic
    @Column(name = "modifier", nullable = true)
    private String modifier;

    @Basic
    @Column(name = "create_date")
    private Date createDate;

    @Basic
    @Column(name = "modify_date")
    private Date modifyDate;

}
