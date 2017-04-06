package com.xwq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 1./sbin/: rabbitmq_plugins.bat list 查看安装的插件列表
 * 2./sbin/: rabbitmq_plugins.bat enable rabbitmq_management 使mq管理可用
 * 3./sbin/: rabbitmq_service.bat stop 关闭mq服务
 * 4./sbin/: rabbitmq_service.bat start 开启mq服务
 * 5.访问http://localhost:15672/打开消息管理界面，登录：guest/guest
 * 
 * @author WQXia
 * @date 2017-04-06 10:40:43
 * @version 1.0
 */
@SpringBootApplication
public class MyAmqpRabbitMqApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MyAmqpRabbitMqApplication.class, args);
	}
	
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Bean
	public Queue queue() {
		return new Queue("my-queue");
	}
	
	@Override
	public void run(String... args) throws Exception {
		rabbitTemplate.convertAndSend("my-queue", "来自RabbitMQ的问候");
	}
}
