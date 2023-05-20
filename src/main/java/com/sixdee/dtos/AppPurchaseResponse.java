package com.sixdee.dtos;

import java.util.ArrayList;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppPurchaseResponse {
	
	private String transactionId ;
	private String timeStamp;

	private String respCode;
	private String respDesc;
	public ArrayList<ProductDetailsResponse> productResponse;
  
	
}
