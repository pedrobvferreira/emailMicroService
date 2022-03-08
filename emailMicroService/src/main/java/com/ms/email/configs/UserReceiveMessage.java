package com.ms.email.configs;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dtos.UserDto;
import com.ms.email.models.UserModel;
import com.ms.email.repositories.UserRepository;


@Component
public class UserReceiveMessage {
	
//	private final UserRepository userRepository;
//
//	@Autowired
//	public UserReceiveMessage(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//	
//	@RabbitListener(queues = {"${userMicroService.rabbitmq.queue}"})
//	public void receive (@Payload UserDto userDto){
//		userRepository.save(UserModel.convert(userDto));
//	}
}
