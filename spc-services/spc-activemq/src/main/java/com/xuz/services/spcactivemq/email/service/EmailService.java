package com.xuz.services.spcactivemq.email.service;

import com.xuz.services.spcactivemq.adapter.MessageAdapter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 处理第三方发送邮件
 *
 * @author xuz
 * @date 2019/7/31 10:55 AM
 */
@Slf4j
@Service
public class EmailService implements MessageAdapter {

	@Value("${msg.subject}")
	private String subject;
	@Value("${msg.content}")
	private String content;
	@Value("${spring.mail.username}")
    private String fromEmail;
	@Autowired
    private  JavaMailSender  javaMailSender;
	@Override
	public void sendMsg(JSONObject body) {
		// 处理发送邮件
		String email = body.getString("email");
		if (StringUtils.isEmpty(email)) {
			return;
		}
		log.info("消息服务平台发送邮件:{} --> 开始", email);
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		// 来自账号
		simpleMailMessage.setFrom(fromEmail);
		// 发送账号
		simpleMailMessage.setTo(email);
		// 标题
		simpleMailMessage.setSubject(subject);
		// 内容
		simpleMailMessage.setText(content.replace("{}", email));
		// 发送邮件
		javaMailSender.send(simpleMailMessage);
		log.info("消息服务平台发送邮件:{}完成", email);
	}

}
