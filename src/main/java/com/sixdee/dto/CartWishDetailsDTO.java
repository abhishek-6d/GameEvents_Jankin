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
@Table(name = "CART_WISH_DETAILS")
public class CartWishDetailsDTO {
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "CART_ID")
	private String cartID;
	
	@Column(name = "CART_TYPE")
	private String CartType;
	
	@Column(name = "CART_DEATILS")
	private String cartdetail;
}
