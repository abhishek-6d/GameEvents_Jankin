package com.sixdee.dto;

import java.security.Timestamp;
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
@Table(name = "MESSAGE_DETAILS")
public class MessageDetailsDTO {

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "MESSAGE_NAME")
	private String msgName;
	
	@Column(name = "MESSAGE_DEVICE_TIME")
	private Date msgDeviceTime;
	
	@Column(name = "MESSAGE_ID")
	private String msgId;
	
	
	
	

	
	
	
	
	
	
	
	
}
