package com.example.notification.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.example.notification.dto.NotificationStatusEnum;
import com.example.notification.notifiers.Notifier;
import com.example.notification.pojo.NotificationTransaction;
import com.example.notification.repositories.NotificationTransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class NotificationTransactionService {
	@Autowired
	private NotificationTransactionRepository notificationTransactionRepository;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private List<Notifier<NotificationTransaction>> notifierList;

	

	public NotificationTransaction addRequest(String requestedTo, String requestedFrom, String body, String subject)
			throws JsonProcessingException {
		NotificationTransaction transaction = new NotificationTransaction();
		transaction.setTo(requestedTo);
		transaction.setFrom(requestedFrom);
		transaction.setBody(body);
		transaction.setSubject(subject);
		transaction.setId(UUID.randomUUID().toString());
		transaction.setRequestedDate(new Date());
		transaction.setDeliveryStatus(NotificationStatusEnum.CREATED.name());

		notificationTransactionRepository.save(transaction);

		ObjectMapper mapper = new ObjectMapper();
		kafkaTemplate.send("notification-test", mapper.writeValueAsString(transaction));

		return transaction;
	}

	public void updateStatus(NotificationTransaction transaction) {
		transaction.setDeliveryStatus(NotificationStatusEnum.DELIVERED.name());
		notificationTransactionRepository.save(transaction);
	}

	@HystrixCommand(groupKey = "notification", commandKey = "send-notification")
	public void sendMailAndUpdateNotification(NotificationTransaction transaction)
			throws RestClientException, JsonProcessingException {
		notifierList.stream().forEach(n -> n.notify(transaction));

		updateStatus(transaction);
	}
}
