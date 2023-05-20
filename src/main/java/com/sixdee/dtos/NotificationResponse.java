package com.sixdee.dtos;

import java.util.ArrayList;

import com.sixdee.dto.MsgResponseDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationResponse {

	
	private String transactionId ;
	private String timeStamp;

	private String respCode;
	private String respDesc;
	public ArrayList<MsgResponseDetails>messageDetails;
	
	
	
	
}
