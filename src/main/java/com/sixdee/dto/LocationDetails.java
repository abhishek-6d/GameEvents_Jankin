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
@Table(name = "LOCATION_DETAILS")
public class LocationDetails {

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "LOCATION_ID")
	private String locationID;
	
	@Column(name = "LATITUDE")
	private String lattitude;
	
	@Column(name = "LANGITUDE")
	private String langitude;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "AREA")
	private String area;
	
	@Column(name = "COUNTRY")
	private String country;
	
	
}
