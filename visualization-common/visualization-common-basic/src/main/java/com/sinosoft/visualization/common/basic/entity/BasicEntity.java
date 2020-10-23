package com.sinosoft.visualization.common.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * BasicEntity
 * 实体基类
 * @author wangyl
 * Description:
 * Created in: 2020/8/20
 * Modified by:
 */
@Data
@NoArgsConstructor
public class BasicEntity <T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;
}
