package com.sixdee.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name = "NOTIFICATION_DISMISS_DETAILS")
public class NotificationDismissDetails {

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "IMAGE")
	private String image;
	
	@Column(name = "MESSAGE_DEVICE_TIME")
	private Date msgDeviceTime;
	

	
}
