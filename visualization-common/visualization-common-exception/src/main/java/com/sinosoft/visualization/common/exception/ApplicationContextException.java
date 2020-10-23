package com.sinosoft.visualization.common.exception;

import com.sinosoft.visualization.common.basic.enums.ResponseCode;

/**
 * CommonException
 *
 * @author wyl
 * Description:
 * Created in: 2020年10月23日15:29:06
 * Modified by: 通用异常
 */
public class ApplicationContextException extends BusinessException {

    public ApplicationContextException(ResponseCode responseCode) {
        super(responseCode);
    }
}
