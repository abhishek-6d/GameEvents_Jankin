package com.sixdee.controller;

import java.util.Map;

import javax.management.Notification;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.dtos.AppPurchaseResponse;
import com.sixdee.dtos.AppVersionResponse;
import com.sixdee.dtos.NotificationResponse;
import com.sixdee.dtos.OsVersionReponse;
import com.sixdee.dtos.Request;
import com.sixdee.dtos.ResponsesDTO;
import com.sixdee.imp.bo.AppPurchaseBL;
import com.sixdee.imp.bo.AppVersionBL;
import com.sixdee.imp.bo.NotificationOpenBL;
import com.sixdee.imp.bo.EventService;
import com.sixdee.imp.bo.OperatingSystemBL;


@RestController
@RequestMapping("/eventListener")
@CrossOrigin(origins = "*")
public class ManagementController {
	
	private Logger logger = LogManager.getLogger(ManagementController.class);
	
	 EventService eventServiceBL=null;	
	 AppPurchaseBL appPurchaseBL=null;
	 NotificationOpenBL notificationBL=null;
	 OperatingSystemBL operatingSystemBL=null;
	 AppVersionBL appVersionBL=null;
	
	@PostMapping({ "/{version}/{eventType}/{phoneNumber}" }) 
	public ResponsesDTO notification(@RequestHeader("Authorization") String bearerToken,@PathVariable("version") String version,@PathVariable("eventType") String eventType,
			@PathVariable("phoneNumber") String phoneNumber, @RequestHeader Map<String, String> headers,HttpServletResponse servletResponse,@RequestBody(required = false) @Valid Request request) {
		ResponsesDTO responsesDTO=null;
		logger.info("...bearerToken :{}  ", bearerToken );
		eventServiceBL=new EventService();
		String txnId=null;
		if (phoneNumber != null && headers != null) {
			for (String key : headers.keySet()) {
				if (key.equalsIgnoreCase("X-CORRELATION-ID"))
					txnId = headers.get(key);
			}
		}
		if(eventType!=null && version!=null && phoneNumber!=null) {
			responsesDTO=eventServiceBL.buildProses(phoneNumber,version,eventType,headers,servletResponse,request);
		}else {
			responsesDTO = new ResponsesDTO();
			responsesDTO.setRespCode("SC0001");
			responsesDTO.setRespDesc("FAILURE");
			responsesDTO.setTimeStamp(String.valueOf(System.currentTimeMillis()));
			responsesDTO.setTransactionId(txnId);
			servletResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		
		
		return responsesDTO;
		
	}
	
}
