package com.example.notification.notifiers;

public interface Notifier<T> {
	public void notify(T t);
}
