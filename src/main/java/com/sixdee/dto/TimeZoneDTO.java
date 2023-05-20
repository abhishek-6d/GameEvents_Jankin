package com.sixdee.dto;

import java.util.TimeZone;

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
@Table(name = "TIME_ZONE_DETAILS")
public class TimeZoneDTO {
	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "ZONE_NAME")
	private String zoneDisplayName;
	
	@Column(name = "ZONE_ID")
	private String zoneId;
	
	@Column(name = "AVAILABLE_ID")
	private String availableIDs;
	

}
