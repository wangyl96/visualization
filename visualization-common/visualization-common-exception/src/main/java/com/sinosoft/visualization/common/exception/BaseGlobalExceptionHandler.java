package com.sinosoft.visualization.common.exception;

import com.sinosoft.visualization.common.basic.enums.ResponseCode;
import com.sinosoft.visualization.common.basic.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * BaseGlobalExceptionHandler
 *
 * @author Dongx
 * Description:
 * Created in: 2019-01-17 8:58
 * Modified by:
 */
@Slf4j
public class BaseGlobalExceptionHandler {

	/**
	 * 违反约束异常
	 */
	protected ServerResponse handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
		log.info("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//		ConvertUtil.convertCVSetToParameterInvalidItemList(constraintViolations);
		return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID.getCode(), e.getMessage());
	}

	/**
	 * 处理验证参数封装错误时异常
	 */
	protected ServerResponse handleConstraintViolationException(HttpMessageNotReadableException e, HttpServletRequest request) {
		log.info("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
		return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID.getCode(), e.getMessage());
	}

	/**
	 * 处理通用自定义业务异常
	 */
	protected ServerResponse handleBusinessException(BusinessException e, HttpServletRequest request) {
		log.info("handleBusinessException start, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
		return ServerResponse.failure(e.getCode(), e.getMessage());
	}

	/**
	 * 处理运行时系统异常（反500错误码）
	 */
	protected ServerResponse handleRuntimeException(RuntimeException e, HttpServletRequest request) {
		log.error("handleRuntimeException start, uri:{}, caused by: ", request.getRequestURI(), e);
		//TODO 可通过邮件、微信公众号等方式发送信息至开发人员、记录存档等操作
		return ServerResponse.failure(ResponseCode.SYSTEM_INNER_ERROR.getCode(), ResponseCode.SYSTEM_INNER_ERROR.getMessage());
	}
}
