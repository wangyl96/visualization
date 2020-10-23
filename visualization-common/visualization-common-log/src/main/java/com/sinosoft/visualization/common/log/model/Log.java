package com.sinosoft.visualization.common.log.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 日志
 *
 * @author tangyi
 * @date 2018/10/31 20:29
 */
@Data
@ToString
public class Log{

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 日志标题
     */
    @NotBlank(message = "日志标题不能为空")
    private String title;

    /**
     * 操作用户的IP地址
     */
    private String ip;

    /**
     * 操作用户代理信息
     */
    private String userAgent;

    /**
     * 操作的URI
     */
    private String requestUri;

    /**
     * 操作的方式
     */
    private String method;

    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 耗时
     */
    private String time;


}
