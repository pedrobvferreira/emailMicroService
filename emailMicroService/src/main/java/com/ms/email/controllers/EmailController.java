package com.ms.email.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;

/**
 * @author Pedro Ferreira
 **/
@RequestMapping("/mail")
public class EmailController {

	@Autowired
    EmailService emailService;
	
	@GetMapping(value = "/{email}", produces = {"application/json","application/xml"})
	public List<EmailModel> getEmailByEmail(@PathVariable("email") String email) {
		return  emailService.findByEmail(email);
	}
	
	@PostMapping(produces = {"application/json", "application/xml"}, 
			consumes = {"application/json", "application/xml"})
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
		try {
			EmailModel emailModel = new EmailModel();
			BeanUtils.copyProperties(emailDto, emailModel);
			emailService.sendEmail(emailModel);
			return ResponseEntity.status(HttpStatus.CREATED).body(emailModel);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }

}
