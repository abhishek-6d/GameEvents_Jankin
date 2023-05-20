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
@Table(name = "CLICK_BUTTON_DETAILS")
public class ClickButtonDetails {
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "BUTTON_ID")
	private String buttonID;
	
	@Column(name = "BUTTIN_TYPE")
	private String buttonType;
	
}
