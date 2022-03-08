package com.ms.user.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.dtos.UserDto;

/**
 * @author Pedro Ferreira
 **/
@Component
public class UserSendMessage {
	
	@Value("${userMicroService.rabbitmq.exchange}")
	String exchange;
	
	@Value("${crud.rabbitmq.routingkey}")
	String routingkey;
	
	public final RabbitTemplate rabbitTemplate;

	@Autowired
	public UserSendMessage(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(UserDto userDto) {
		rabbitTemplate.convertAndSend(exchange, routingkey, userDto);
	}
}
