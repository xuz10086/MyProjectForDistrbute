package com.xuz.services.spcrabbitmq.mq;

import com.alibaba.fastjson.JSONObject;
import com.xuz.myproject.constants.Constants;
import com.xuz.services.spcrabbitmq.adapter.MessageAdapter;
import com.xuz.services.spcrabbitmq.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 消息队列中消息统一处理
 *
 * @author xuz
 * @date 2019/7/31 11:06 AM
 */
@Component
@Slf4j
public class ConsumerDistribute {
    @Autowired
    private EmailService emailService;
    private MessageAdapter messageAdapter;
    // 监听生产-消费模型队列
    private static final String MESSAGEQUEUE = "messages_queue_01";

    /**
     * 监听生产-消费模型的消息
     *
     * @param json
     * @return void
     * @author xuz
     * @date 2019/7/31 11:01 AM
     */
    @RabbitListener(queues = MESSAGEQUEUE)
    public void distribute(String json) {
        log.info("#####消息服务平台接受消息内容:{}#####", json);
        if (StringUtils.isEmpty(json)) {
            return;
        }
        JSONObject rootJSON = JSONObject.parseObject(json);
        JSONObject header = rootJSON.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");

        if (StringUtils.isEmpty(interfaceType)) {
            return;
        }
        // 判断接口类型是否为发邮件
        if (interfaceType.equals(Constants.MSG_EMAIL)) {
            messageAdapter = emailService;
        }
        if (messageAdapter == null) {
            return;
        }
        JSONObject acceptorJson = rootJSON.getJSONObject("acceptor");
        messageAdapter.sendMsg(acceptorJson);
    }

}