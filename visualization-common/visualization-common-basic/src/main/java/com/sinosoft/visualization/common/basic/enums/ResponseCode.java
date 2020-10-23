package com.sinosoft.visualization.common.basic.enums;

import lombok.Getter;

/**
 * ResponseCode
 *
 * @author wangyl
 * Description:
 * Created in: 2020年10月23日15:36:33
 * Modified by:
 */
@Getter
public enum ResponseCode {
    /* 成功状态码 */
    SUCCESS(1, "成功"),
    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    APPLICATIONCONTEXY_IS_EMPTY(10002, "applicationContext为空"),
    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),
    DATA_NOT_FOUND(50001, "数据未找到"),;

    private Integer code;

    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResponseCode item : ResponseCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResponseCode item : ResponseCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }
}
