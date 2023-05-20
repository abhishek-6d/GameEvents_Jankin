package com.sixdee.dto;

import java.util.List;

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
@Table(name = "CUSTOM_EVENT_DETAILS")
public class CustomEventDetails {
	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "VALUE")
	private String values;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
}
