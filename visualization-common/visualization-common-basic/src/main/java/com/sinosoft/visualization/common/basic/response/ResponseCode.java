package com.sinosoft.visualization.common.basic.response;

import lombok.Getter;

/**
 * Created in: 2019年1月3日11:26:56
 * @author Dongx
 */
@Getter
public enum ResponseCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),
    PARAM_IS_ALL_BLANK(10005,"参数不可同时为空"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_NOT_ROLES(20006, "用户无任何权限"),
    USER_PASSWORD_ERROR(20007, "密码错误"),
    USER_UPDATE_ERROR(2008, "头像未指定"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-599999 */
    DATA_NOT_FOUND(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限"),

    /* token错误：80001-89999 */
    TOKEN_EXPIRED(80001, "认证已过期"),
    TOKEN_FORMAT_ERROR(80002, "认证格式不正确"),
    TOKEN_NOT_SIGNATURE(80003, "认证无效"),
    SC_INTERNAL_SERVER_ERROR(80004, "无法解析token"),
    REMOTE_TOKEN_NOT_SIGNATURE(80005, "远端认证无效"),
    REMOTE_CONNECTION_FAILED(80006, "远端连接失败"),

    /* 服务错 */
    REMOTE_SERVER_ERROR(90001, "未查询出信息"),
    SERVER_QUERY_BASEINFO(90002, "该系统信息不存在"),
    REMOTE_QUERY_OPERATION_ORDER(9003, "未查到对应工单！");



    ;
    
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
