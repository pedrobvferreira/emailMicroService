package com.ms.email.services;

import java.time.LocalDateTime;
import java.util.List;

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
	
	/** Method to find Email **/
	public List<EmailModel> findByEmail(String email) {
		return emailRepository.findByEmail(email);
	}

	/** Method to send Email **/
	public EmailModel sendEmail(EmailModel emailModel) {
		emailModel.setSendDate(LocalDateTime.now());
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(emailModel.getEmailFrom());
			msg.setTo(emailModel.getEmailTo());
			msg.setSubject(emailModel.getSubject());
			msg.setText(emailModel.getBody());
			emailSender.send(msg);
			
			emailModel.setStatusEmail(StatusEmail.SENT.getValue());
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR.getValue());
		}
		return emailRepository.save(emailModel);
	}
}
