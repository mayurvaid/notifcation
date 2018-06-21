package com.example.notification.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.stereotype.Component;

@Component
public class KafkaErrorHandler implements ErrorHandler{
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Override
	public void handle(Exception thrownException, ConsumerRecord<?, ?> data) {
		kafkaTemplate.send("notification-test1-error", data.value().toString());
	}

}
