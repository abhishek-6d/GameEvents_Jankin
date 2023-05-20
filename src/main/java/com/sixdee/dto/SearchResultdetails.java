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
@Table(name = "SERCH_RESULT_DETAILS")
public class SearchResultdetails {

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "SEACHING_ID")
	private String searchId;
	
	@Column(name = "SEACHING_TYPE")
	private String searhType;
	
	
}
