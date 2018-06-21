package com.example.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.pojo.NotificationTransaction;
import com.example.notification.service.NotificationTransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class NotificationController {

	@Autowired
	private NotificationTransactionService notificationTransactionService;

	

	@PostMapping("/")
	public NotificationTransaction sendMail(@RequestBody NotificationRequest notificationRequest) throws JsonProcessingException {
		return notificationTransactionService.addRequest(notificationRequest.getTo(), notificationRequest.getFrom(),
				notificationRequest.getBody(), notificationRequest.getSubject());
	}

}
