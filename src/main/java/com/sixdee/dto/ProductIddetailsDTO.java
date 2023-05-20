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
@Table(name = "PRODUCT_ID_DETAILS")
public class ProductIddetailsDTO {
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "PRODUCT_ID")
	private String proId;
	
	@Column(name = "PRODUCT_TYPE")
	private String productype;
	
	@Column(name = "PRODUCT_DETAILS")
	private String prodctDetails;
	
	
	
}
