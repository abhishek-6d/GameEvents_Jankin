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
@Table(name = "CART_REMOVE_DETAILS")
public class CartRemoveDetailsDTO {
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "CART_REMOVE_ID")
	private String cartRemoveId;
	
	@Column(name = "CART_REMOVE_TYPE")
	private String cartRemoveType;
	
	@Column(name = "CART_REMOVE_DEATILS")
	private String cartRemoveDetails;
}
