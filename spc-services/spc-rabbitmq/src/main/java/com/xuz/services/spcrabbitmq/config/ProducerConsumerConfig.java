package com.xuz.services.spcrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者消费者模式的配置,包括一个队列和两个对应的消费者
 *
 * @author xuz
 */
@Configuration
public class ProducerConsumerConfig {
	@Autowired
	RabbitConfig rabbitconfig;
	@Value("${email.messages.queue}")
	private String emailQueue;

	@Bean
	public Queue myQueue() {
	   Queue queue=new Queue(emailQueue);
	   return queue;
	}
	    
}
