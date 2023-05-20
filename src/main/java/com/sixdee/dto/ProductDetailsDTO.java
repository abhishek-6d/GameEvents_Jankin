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
@Table(name = "PRODUCT_DETAILS")
public class ProductDetailsDTO {


	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "PRODUCT_ID")
	private String productId;

	
	@Column(name = "PRODUCT_NAME")
	private String procuctName;
	
	@Column(name = "PRICE")
	private String price;
	
	@Column(name = "QUANTITY")
	private String quantity;
	
	@Column(name = "CURRENCY")
	private String currency;
	
}
