package com.xuz.services.spcactivemq.mq;

import com.xuz.myproject.constants.Constants;
import com.xuz.services.spcactivemq.adapter.MessageAdapter;
import com.xuz.services.spcactivemq.email.service.EmailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

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
	private static final String MESSAGEQUEUE = "messages_queue_01";

	/**
	 * 监听消息
	 *
	 * @param json 
	 * @return void
	 * @author xuz
	 * @date 2019/7/31 11:01 AM
	 */
	@JmsListener(destination = MESSAGEQUEUE)
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
