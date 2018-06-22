package com.example.notification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.pojo.NotificationTransaction;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		NotificationRequest request = new NotificationRequest();
		request.setTo("test");
		request.setFrom("from");
		request.setSubject("subject");
		request.setBody("body");

		NotificationTransaction transaction = this.restTemplate.postForObject("http://localhost:" + port + "/", request,
				NotificationTransaction.class);

		assertNotNull(transaction);
	}
}