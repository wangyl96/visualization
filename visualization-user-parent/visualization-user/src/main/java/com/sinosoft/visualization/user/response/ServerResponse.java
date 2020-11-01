package com.sinosoft.visualization.user.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 服务响应 
 * @author dongx
 * @param <T>
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Result {

    private static final long serialVersionUID = 1L;
    
    private Integer code;

    private String message;

    private T data;
    
    private ServerResponse(){
        
    }
    
    private ServerResponse(Integer code)  {
        this.code = code;
    }

    private ServerResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ServerResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private ServerResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ServerResponse<T> success(Integer code) {
        return new ServerResponse<>(code);
    }

    public static <T> ServerResponse<T> success(String message) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static <T> ServerResponse<T> success(String message, T data) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ServerResponse<T> failure(Integer code) {
        return new ServerResponse<>(code);
    }

    public static <T> ServerResponse<T> failure(String message) {
        return new ServerResponse<>(ResponseCode.PARAM_IS_INVALID.getCode(), message);
    }

    public static <T> ServerResponse<T> failure(Integer code, String message) {
        return new ServerResponse<>(code, message);
    }

    public static <T> ServerResponse<T> failure(String message, T data) {
        return new ServerResponse<>(ResponseCode.SYSTEM_INNER_ERROR.getCode(), message, data);
    }

    public static <T> ServerResponse<T> failure(Integer code, String message, T data) {
        return new ServerResponse<>(code, message, data);
    }

}
