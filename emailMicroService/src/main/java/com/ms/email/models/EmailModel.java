package com.ms.email.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ms.email.enums.StatusEmail;

@Entity
@Table(name = "email_model")
public class EmailModel implements Serializable {

	private static final long serialVersionUID = -3013122315662714947L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String reference;
	private String emailFrom;
	private String emailTo;
	private String subject;
	@Column(columnDefinition = "TEXT")
	private String text;
	private LocalDateTime sendDate;
	private StatusEmail statusEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}

	public StatusEmail getStatusEmail() {
		return statusEmail;
	}

	public void setStatusEmail(StatusEmail statusEmail) {
		this.statusEmail = statusEmail;
	}
	
	@Override
	public String toString() {
		return "Email{" + "id=" + this.id + ", emailFrom='" + this.emailFrom + '\'' + ", emailTo='" + this.emailTo + '\'' + '}';
	}

}
