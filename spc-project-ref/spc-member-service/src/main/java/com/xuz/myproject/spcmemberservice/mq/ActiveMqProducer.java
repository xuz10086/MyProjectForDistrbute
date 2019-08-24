package com.xuz.myproject.spcmemberservice.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 注册邮件消息生产
 *
 * @author xuz
 * @date 2019/7/31 10:12 AM
 */
@Service
public class ActiveMqProducer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	public void sendMsg(Destination destination, String json) {
		jmsMessagingTemplate.convertAndSend(destination, json);
	}
}
