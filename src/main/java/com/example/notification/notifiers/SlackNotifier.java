package com.example.notification.notifiers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.notification.pojo.NotificationTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SlackNotifier<T extends NotificationTransaction> implements Notifier<T> {

	@Override
	public void notify(NotificationTransaction transaction) {
		RestTemplate template = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> data = new HashMap<>();

		try {
			data.put("text", mapper.writeValueAsString(transaction));

			template.postForObject("https://hooks.slack.com/services/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",
					mapper.writeValueAsString(data), String.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
