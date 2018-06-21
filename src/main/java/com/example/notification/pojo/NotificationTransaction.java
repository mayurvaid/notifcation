package com.example.notification.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("notification_transaction")
public class NotificationTransaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3625034124756266450L;

	@PrimaryKeyColumn(name="id",type=PrimaryKeyType.PARTITIONED)
	private String id;
	
	@Column("requested_from")
	private String from;
	
	@Column("requested_to")
	private String to;
	
	@Column("subject")
	private String subject;
	
	@Column("body")
	private String body;
	
	@Column("delivery_status")
	private String deliveryStatus;

	@Column("request_date")
	private Date requestedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
}
