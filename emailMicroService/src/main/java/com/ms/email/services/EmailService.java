package com.ms.email.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;

/**
 * @author Pedro Ferreira
 **/
@Service
public class EmailService {

	private EmailRepository emailRepository;
    private JavaMailSender emailSender;
	
	@Autowired
	public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
		this.emailRepository = emailRepository;
		this.emailSender = emailSender;
	}

	/** Method to send Email **/
	public EmailModel sendEmail(EmailModel emailModel) {
		emailModel.setSendDate(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getBody());
			emailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT.getValue());
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR.getValue());
		}
		return emailRepository.save(emailModel);
	}
}
