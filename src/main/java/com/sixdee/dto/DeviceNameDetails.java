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
@Table(name = "DEVICE_NAME_DETAILS")
public class DeviceNameDetails {

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	
	
	@Column(name = "DEVICE_TYPE")
	private String deviceType;
	
	@Column(name = "DEVICE_NAME")
	private String deviceName;
	
	
	
	
	
	

	
	
	
	
	
	
	
	

}
