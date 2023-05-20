package com.sixdee.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppVersionResponse {

	private String transactionId ;
	private String timeStamp;
	private String respCode;
	private String respDesc;
}
