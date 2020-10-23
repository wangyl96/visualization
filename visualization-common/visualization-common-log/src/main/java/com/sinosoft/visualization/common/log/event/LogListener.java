package com.sinosoft.visualization.common.log.event;

import com.sinosoft.visualization.common.log.model.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 异步监听日志事件
 *
 * @author tangyi
 * @date 2019/3/12 23:59
 */
@Slf4j
public class LogListener {

//    @Autowired
//    private UserServiceClient userServiceClient;
//
//    public LogListener(UserServiceClient userServiceClient) {
//        this.userServiceClient = userServiceClient;
//    }

    /**
     * 异步记录日志
     *
     * @param event event
     */
    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        Log logVO = (Log) event.getSource();
        log.info("异步监听日志：{}", logVO.toString());
//        userServiceClient.saveLog(log);
    }
}
