package com.ms.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dtos.UserDto;
import com.ms.email.models.EmailModel;
import com.ms.email.models.UserModel;
import com.ms.email.repositories.UserRepository;
import com.ms.email.services.EmailService;

/**
 * @author Pedro Ferreira
 **/
@Component
public class UserReceiveMessage {
	
	UserRepository userRepository;
	EmailService emailService;
	
	@Autowired
	public UserReceiveMessage(UserRepository userRepository, EmailService emailService) {
		this.userRepository = userRepository;
		this.emailService = emailService;
	}
	
	@RabbitListener(queues = "${user.rabbitmq.queue}")
	public void receiveMessage(@Payload UserDto userDto) {
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		userRepository.save(userModel);
		
		EmailModel emailModel = new EmailModel();
		emailModel.setEmailFrom("companhia@gmail.com");
		emailModel.setEmailTo(userDto.getEmail());
		emailModel.setSubject("Criação do Email");
		emailModel.setBody("MicroServico de Envio de Email");
		emailService.sendEmail(emailModel);
		
	}
}
