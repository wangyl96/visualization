package com.sinosoft.visualization.common.log.event;

import com.sinosoft.visualization.common.log.model.Log;
import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 *
 * @author tangyi
 * @date 2019/3/12 23:58
 */
public class LogEvent extends ApplicationEvent {
    public LogEvent(Log source) {
        super(source);
    }
}
