package com.sixdee.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OsVersionReponse {

	
	private String osversion;
	private String osName;
	private String osArch;
	private String transactionId ;
	private String timeStamp;
}
