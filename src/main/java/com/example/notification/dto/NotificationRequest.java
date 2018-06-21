package com.example.notification.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Valid
public class NotificationRequest {
	@NotEmpty
	@Email
	private String from;

	@NotEmpty
	@Email
	private String to;

	@NotEmpty
	@Size(max = 100)
	private String subject;

	@NotEmpty
	@Size(max = 500)
	private String body;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
