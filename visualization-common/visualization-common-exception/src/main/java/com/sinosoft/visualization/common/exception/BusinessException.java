package com.sinosoft.visualization.common.exception;

import com.sinosoft.visualization.common.basic.enums.ResponseCode;
import lombok.Getter;

/**
 * BusinessException
 *
 * @author Dongx
 * Description:
 * Created in: 2019-01-15 10:15
 * Modified by:
 */
@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	private String message;
	
	private ResponseCode responseCode;
	
	public BusinessException(Integer code, String msg) {
		this.code = code;
		this.message = msg;
	}

	public BusinessException(ResponseCode responseCode) {
		this.code = responseCode.getCode();
		this.message = responseCode.getMessage();
	}
}
