package com.sixdee.dtos;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
public class ProductDetailsResponse {
	  private String productId;
		private String price;
		private String procuctName;
		private String quantity;
	    private String currency;
}
