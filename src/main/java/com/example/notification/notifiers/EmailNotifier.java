package com.example.notification.notifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.notification.pojo.NotificationTransaction;

@Component
public class EmailNotifier<T extends NotificationTransaction> implements Notifier<T> {
	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void notify(NotificationTransaction t) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(t.getTo());
		message.setSubject(t.getSubject());
		message.setText(t.getBody());
		message.setFrom(t.getFrom());

		emailSender.send(message);

	}

}
