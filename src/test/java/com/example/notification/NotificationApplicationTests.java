package com.example.notification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.notification.controller.NotificationController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationApplicationTests {
	@Autowired
	private NotificationController notificationController;
	@Test
    public void contextLoads() throws Exception {
		assertNotNull(notificationController);
    }

}
