package com.sinosoft.visualization.user.response;

import lombok.Builder;
import lombok.Data;

/**
 * ParameterInvalidItem
 *
 * @author Dongx
 * Description:
 * Created in: 2019-01-17 9:27
 * Modified by:
 */
@Data
@Builder
public class ParameterInvalidItem {
	
	private String filedName;
	
	private String errorMessage;
}
