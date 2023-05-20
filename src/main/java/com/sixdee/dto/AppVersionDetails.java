package com.sixdee.dto;

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
@Table(name = "APP_VERSION_DETAILS")
public class AppVersionDetails {

	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "CURRENT_VERSION")
	private String current_version;
	
	
	
	
	
}
