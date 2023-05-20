package com.sixdee.dtos;

import java.util.List;


import com.sixdee.dto.CustomEventDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Request {
	private String transactionId ;
	private String timeStamp;
	private String productId;
	private String price;
	private String productName;
	private String quantity;
	private String currency;
	private String message_name;
	private String message_id;
	private String message_title;
	private String image;
	private String message_device_time;
	private String version;
	private String searchId;
	private String searhType;
	
	private String buttonId;
	private String buttonType;
	
	private String intendId;
	private String intendType;
	
	private String locationId;
	private String lattitude;
	private String langitude;
	private String address;
	private String area;
	private String country;

	private String videoId;
	private String videoTitle;
	private String deviceId;
	private String deviceType;
	private String deviceName;
	private String timeZoneId;
	private String currentTime;
	private String currentDate;
	private String onclickId;
	private String clickType;
	private String pageId;
	private String pageDetails;
	//----------------reward------------------
	private String rewardId;
	private String rewardType;
	private String rewardPoint;
	private String rewardtitle;
	//-----------------gamedetails--------
	
	private String gameId;
	private String gametype;
	private String gametitle;
	//-----------------ProductIddetails--------
	private String proId;
	private String productype;
	private String prodctDetails;
	//-----------------Add/wishlist/remove To  cart--------
	
		private String cartID;
		private String CartType;
		private String cartdetail;
		
		private List<CustomEventDetails> customEvent;
		
		
		
			
	
	
	
	
	
	
	
	
	
}
