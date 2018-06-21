package com.example.notification.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;

import com.example.notification.pojo.NotificationTransaction;
import com.example.notification.service.KafkaErrorHandler;
import com.example.notification.service.NotificationTransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	@Autowired
	private KafkaErrorHandler kafkaErrorHandler;

	@Autowired
	private NotificationTransactionService notificationTransactionService;
	
	@Value("${kafka.server:10.5.37.220:9092}")
	private String bootstrapServers;

	@Value("${kafka.groupId:notification-test4}")
	private String groupId;
	
	@Value("${kafka.topic:notification-test}")
	private String kafkaTopicName;

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
		factory.getContainerProperties().setErrorHandler(kafkaErrorHandler);
		return factory;
	}

	@KafkaListener(topics = "notification-test", containerFactory = "kafkaListenerContainerFactory")
	public void listen(ConsumerRecord<?, ?> record, Acknowledgment ack) throws Exception {
		System.out.println("Received Messasge in group foo: " + record);
		ack.acknowledge();

		ObjectMapper mapper = new ObjectMapper();
		String data = record.value().toString();
		NotificationTransaction transaction = mapper.readValue(data, NotificationTransaction.class);

		notificationTransactionService.sendMailAndUpdateNotification(transaction);
	}

	@KafkaListener(topics = "notification-test1-error", containerFactory = "kafkaListenerContainerFactory")
	public void listenErrorMessage(ConsumerRecord<?, ?> record, Acknowledgment ack) throws Exception {
		System.out.println("Received Unacknowledged message in group foo: " + record);
		Thread.sleep(2000);

		ack.acknowledge();

		ObjectMapper mapper = new ObjectMapper();
		String data = record.value().toString();
		NotificationTransaction transaction = mapper.readValue(data, NotificationTransaction.class);

		notificationTransactionService.sendMailAndUpdateNotification(transaction);
	}

}