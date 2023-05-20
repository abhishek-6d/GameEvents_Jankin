package com.sixdee.imp.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.server.reactive.ChannelSendOperator;

import com.sixdee.controller.ManagementController;
import com.sixdee.dao.TableDetailsDAO;
import com.sixdee.dto.AppVersionDetails;
import com.sixdee.dto.CartDetaisDTO;
import com.sixdee.dto.CartWishDetailsDTO;
import com.sixdee.dto.ClickButtonDetails;
import com.sixdee.dto.CurrentTimeDetails;
import com.sixdee.dto.DeviceIdDetails;
import com.sixdee.dto.DeviceNameDetails;
import com.sixdee.dto.GamedetailsDTO;
import com.sixdee.dto.IntentendataDetails;
import com.sixdee.dto.LocationDetails;
import com.sixdee.dto.MessageDetailsDTO;
import com.sixdee.dto.NotificationDismissDetails;
import com.sixdee.dto.OsVersionDetails;
import com.sixdee.dto.ProductDetailsDTO;
import com.sixdee.dto.ProductIddetailsDTO;
import com.sixdee.dto.RewardDetailsDTO;
import com.sixdee.dto.SearchResultdetails;
import com.sixdee.dto.UserRegisterDTO;
import com.sixdee.dto.VideoViewDetails;

import com.sixdee.dto.CustomEventDetails;
import com.sixdee.dtos.Request;
import com.sixdee.dtos.ResponsesDTO;
import com.sixdee.dto.TimeZoneDTO;
import com.sixdee.util.AppCache;

public class EventService {
	private Logger logger = LogManager.getLogger(EventService.class);

	public ResponsesDTO buildProses(String phoneNumber, String version,String eventType, Map<String, String> headers,
			HttpServletResponse servletResponse, Request request) {

		
		String txnId = null;
		Boolean isUserRegister = false;
		TableDetailsDAO tableDetailsDAO = null;
		ResponsesDTO responsesDTO = null;
		ProductDetailsDTO productDetailsDTO = null;
		MessageDetailsDTO messageDetailsDTO = null;
		OsVersionDetails osVersion = null;
		AppVersionDetails appverrsiondetails=null;
		SearchResultdetails serchResultdetails=null;
		Boolean appVersionCheck=false;
		String version1=null;
		CurrentTimeDetails CurrentTimeDetails;
		ClickButtonDetails clickButtonDetails=null;
		VideoViewDetails videoViewDetails=null;
		DeviceIdDetails deviceIDDetails=null;
		TimeZoneDetilsBL timeZoneDetilsBL=null;
		TimeZoneDTO timeZoneDTO =null;
		RewardDetailsDTO rewardDetailsDTO=null;
		GamedetailsDTO gamedetailsDTO=null;
		ProductIddetailsDTO productIddetailsDTO=null;
		CartDetaisDTO cartDetaisDTO=null;
		NotificationDismissDetails notificationDismissDetails=null;
		CartWishDetailsDTO cartWishDetailsDTO=null;
		UserRegisterDTO userRegisterDTO=null;
		AppVersionDetails appVersionDetails=null;
		DeviceNameDetails deviceDetails=null;
		LocationDetails locationDetails=null;
		IntentendataDetails IntentendataDetails=null;
		CustomEventDetails customEvent =null;
		List<CustomEventDetails>customEventList=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		try {
			customEventList=new ArrayList<CustomEventDetails>();
			timeZoneDTO=new TimeZoneDTO();
			timeZoneDetilsBL=new TimeZoneDetilsBL();
			tableDetailsDAO = new TableDetailsDAO();
			responsesDTO = new ResponsesDTO();
			if (phoneNumber != null && headers != null) {
				for (String key : headers.keySet()) {
					if (key.equalsIgnoreCase("X-CORRELATION-ID"))
						txnId = headers.get(key);
				}
				logger.info("...txnId:{},Msisdn:{}  ", txnId, phoneNumber, eventType);
				
				if (eventType.equalsIgnoreCase("first_Visit")) {
					userRegisterDTO =new UserRegisterDTO();
					userRegisterDTO.setMsisdn(phoneNumber);
				    userRegisterDTO.setVersion(version);
				    tableDetailsDAO.insertUserDetials(userRegisterDTO);
				    responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
				 } else if (eventType.equalsIgnoreCase("in_app_purchase")) {
					productDetailsDTO = new ProductDetailsDTO();
					productDetailsDTO.setMsisdn(phoneNumber);
					productDetailsDTO.setProductId(request.getProductId());
					productDetailsDTO.setProcuctName(request.getProductName());
					productDetailsDTO.setPrice(request.getPrice());
					productDetailsDTO.setQuantity(request.getQuantity());
					productDetailsDTO.setCurrency(request.getCurrency());

					tableDetailsDAO.insertProductDetials(productDetailsDTO);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);

				} else if (eventType.equalsIgnoreCase("notification_open")) {
					messageDetailsDTO = new MessageDetailsDTO();
					messageDetailsDTO.setMsisdn(phoneNumber);
					messageDetailsDTO.setMsgName(request.getMessage_name());
					messageDetailsDTO.setMsgId(request.getMessage_id());
					if (request.getMessage_device_time() != null) {

						messageDetailsDTO.setMsgDeviceTime(sdf.parse(request.getMessage_device_time()));
					} else {
						messageDetailsDTO.setMsgDeviceTime(new Date());
					}
					tableDetailsDAO.insertNotificationMessageDetials(messageDetailsDTO);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
					
				} else if (eventType.equalsIgnoreCase("notification_dismiss")) {
					notificationDismissDetails = new NotificationDismissDetails();
					notificationDismissDetails.setMsisdn(phoneNumber);
					notificationDismissDetails.setTitle(request.getMessage_title());
					notificationDismissDetails.setImage(request.getImage());
					
					if (request.getMessage_device_time() != null) {

						notificationDismissDetails.setMsgDeviceTime(sdf.parse(request.getMessage_device_time()));
					} else {
						notificationDismissDetails.setMsgDeviceTime(new Date());
					}
					
					tableDetailsDAO.insertDismissMessageDetials(notificationDismissDetails);
					responsesDTO = tableDetailsDAO.commanMethod(txnId, servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);

				}
				else if (eventType.equalsIgnoreCase("os_version")) {
					osVersion = new OsVersionDetails();
					if (version != null) {
						osVersion.setOsVersion(version);
						

					} else {
						osVersion.setOsVersion(System.getProperty("os.version"));
					}

					osVersion.setMsisdn(phoneNumber);
					tableDetailsDAO.insertOsVersion(osVersion);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);

				}else if(eventType.equalsIgnoreCase("app_version")) {
					if(version!=null) {
						version1=version;
					}else {
						version1="0.0";
					}
					appVersionDetails =new AppVersionDetails();
					appVersionDetails.setCurrent_version(version1);
					appVersionDetails.setMsisdn(phoneNumber);
					tableDetailsDAO.inserAppVersiondetails(appVersionDetails);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
					
				}else if(eventType.equalsIgnoreCase("serchResult")) {
					
					serchResultdetails=new SearchResultdetails();
					serchResultdetails.setMsisdn(phoneNumber);
					serchResultdetails.setSearchId(request.getSearchId());
					serchResultdetails.setSearhType(request.getSearhType());
					tableDetailsDAO.insersearchDetails(serchResultdetails);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
				}
				else if(eventType.equalsIgnoreCase("video_view")) {
					
					videoViewDetails=new VideoViewDetails();
				    videoViewDetails.setMsisdn(phoneNumber);
					videoViewDetails.setVideoId(request.getVideoId());
					videoViewDetails.setVideoTitle(request.getVideoTitle());
					tableDetailsDAO.inserViewVideo(videoViewDetails);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
				}else if(eventType.equalsIgnoreCase("location")) {
					
					locationDetails =new LocationDetails();
					locationDetails.setMsisdn(phoneNumber);
					locationDetails.setLocationID(request.getLocationId());
					locationDetails.setAddress(request.getAddress());
					locationDetails.setArea(request.getArea());
					locationDetails.setCountry(request.getCountry());
					locationDetails.setLangitude(request.getLangitude());
					locationDetails.setLattitude(request.getLattitude());
					tableDetailsDAO.inserloctiondetails(locationDetails);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
					
					
				}else if(eventType.equalsIgnoreCase("device_id")) {
					
					deviceIDDetails =new DeviceIdDetails();
					deviceIDDetails.setMsisdn(phoneNumber);
					deviceIDDetails.setDeviceId(request.getDeviceId());
					deviceIDDetails.setDeviceType(request.getDeviceType());
					tableDetailsDAO.insertdeviceDetails(deviceIDDetails);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
					
				}else if(eventType.equalsIgnoreCase("Device_name")) {
					
					deviceDetails =new DeviceNameDetails();
					deviceDetails.setMsisdn(phoneNumber);
					deviceDetails.setDeviceType(request.getDeviceType());
					deviceDetails.setDeviceName(request.getDeviceName());
					tableDetailsDAO.insertdeviceDetails(deviceDetails);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					
				}else if(eventType.equalsIgnoreCase("time_zone")) {
					if(request.getTimeZoneId()!=null) {
						timeZoneDTO =timeZoneDetilsBL.timeZone(request.getTimeZoneId());
					}else {
						timeZoneDTO =timeZoneDetilsBL.timeZone(null);
					}
					
					tableDetailsDAO.insertdeviceDetails(timeZoneDTO);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
					
				}else if(eventType.equalsIgnoreCase("current_time")) {
					 CurrentTimeDetails=new CurrentTimeDetails();
					CurrentTimeDetails.setMsisdn(phoneNumber);
					CurrentTimeDetails.setCurrentTime(new java.sql.Date(System.currentTimeMillis()));
					tableDetailsDAO.insertcurrentTime(CurrentTimeDetails);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
					
					
				}else if(eventType.equalsIgnoreCase("click")) {
					clickButtonDetails=new ClickButtonDetails();
					clickButtonDetails.setButtonID(request.getButtonId());
					clickButtonDetails.setMsisdn(phoneNumber);
					clickButtonDetails.setButtonType(request.getButtonType());
					tableDetailsDAO.insertbuttonDetails(clickButtonDetails);
					responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
				}else if(eventType.equalsIgnoreCase("intent_data")) {
					 IntentendataDetails =new IntentendataDetails();
					 IntentendataDetails.setIntendId(request.getIntendId());
					 IntentendataDetails.setIntendType(request.getIntendType());
					 IntentendataDetails.setMsisdn(phoneNumber);
					 tableDetailsDAO.insertdeviceDetails(IntentendataDetails);
                	 responsesDTO = new ResponsesDTO();
                	 responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
 					 servletResponse.setStatus(HttpStatus.SC_OK);
				}
                 else if(eventType.equalsIgnoreCase("reward_details")) {
                	 rewardDetailsDTO=new RewardDetailsDTO();
                	 rewardDetailsDTO.setMsisdn(phoneNumber);
                	 rewardDetailsDTO.setRewardId(request.getRewardId());
                	 rewardDetailsDTO.setRewardPoint(request.getRewardPoint());
                	 rewardDetailsDTO.setRewardType(request.getRewardPoint());
                	 rewardDetailsDTO.setRewardtitle(request.getRewardtitle());
                	 tableDetailsDAO.insertdeviceDetails(rewardDetailsDTO);
                	 responsesDTO = new ResponsesDTO();
                	 responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
 					 servletResponse.setStatus(HttpStatus.SC_OK);
                	 
				}else if(eventType.equalsIgnoreCase("game_Id")) {
					gamedetailsDTO=new GamedetailsDTO();
					gamedetailsDTO.setGameId(request.getGameId());
					gamedetailsDTO.setGametype(request.getGametype());
					gamedetailsDTO.setGametitle(request.getGametitle());
					gamedetailsDTO.setMsisdn(phoneNumber);
					 tableDetailsDAO.insertdeviceDetails(gamedetailsDTO);
					 responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
						servletResponse.setStatus(HttpStatus.SC_OK);
				}else if(eventType.equalsIgnoreCase("product_Id")) {
					productIddetailsDTO=new ProductIddetailsDTO();
					productIddetailsDTO.setMsisdn(phoneNumber);
					productIddetailsDTO.setProdctDetails(request.getProdctDetails());
					productIddetailsDTO.setProductype(request.getProductype());
					productIddetailsDTO.setProId(request.getProId());
					 tableDetailsDAO.insertdeviceDetails(productIddetailsDTO);
					 responsesDTO = new ResponsesDTO();
					 responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
						servletResponse.setStatus(HttpStatus.SC_OK);
					
				}
                 else if(eventType.equalsIgnoreCase("add_to_cart")) {
                	 cartDetaisDTO=new CartDetaisDTO();
                	 cartDetaisDTO.setCartID(request.getCartID());
                	 cartDetaisDTO.setCartType(request.getCartType());
                	 cartDetaisDTO.setCartdetail(request.getCartdetail());
                	 cartDetaisDTO.setMsisdn(phoneNumber);
                	 tableDetailsDAO.insertdeviceDetails(cartDetaisDTO);
                	 responsesDTO = new ResponsesDTO();
                	 responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
 					servletResponse.setStatus(HttpStatus.SC_OK);
				}
               else if(eventType.equalsIgnoreCase("add_to_wishlist")) {
            	   cartWishDetailsDTO=new CartWishDetailsDTO();
            	   cartWishDetailsDTO.setCartID(request.getCartID());
            	   cartWishDetailsDTO.setCartType(request.getCartType());
            	   cartWishDetailsDTO.setCartdetail(request.getCartdetail());
            	   cartWishDetailsDTO.setMsisdn(phoneNumber);
              	 tableDetailsDAO.insertdeviceDetails(cartWishDetailsDTO);
              	 responsesDTO = new ResponsesDTO();
            	 responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
            	   
            	   
                }
               else if(eventType.equalsIgnoreCase("remove_from_cart")) {
            	   cartWishDetailsDTO=new CartWishDetailsDTO();
            	   cartWishDetailsDTO.setCartID(request.getCartID());
            	   cartWishDetailsDTO.setCartType(request.getCartType());
            	   cartWishDetailsDTO.setCartdetail(request.getCartdetail());
            	   cartWishDetailsDTO.setMsisdn(phoneNumber);
              	 tableDetailsDAO.insertdeviceDetails(cartWishDetailsDTO);
              	 
              	 responsesDTO = new ResponsesDTO();
            	 responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
               }
               else if(eventType.equalsIgnoreCase("custom_event")) {
            	   
            	   for(CustomEventDetails customEvents:request.getCustomEvent()) {
            		   customEvent=new CustomEventDetails();
            		   customEvent.setId(customEvents.getId());
            		   customEvent.setValues(customEvents.getValues());
            		   customEvent.setMsisdn(phoneNumber);
            		   customEventList.add(customEvent);
            		   
            	   }
               }
				
				Boolean flag=tableDetailsDAO.insertListofCustomDetails(customEventList);
				responsesDTO = new ResponsesDTO();
           	     responsesDTO=tableDetailsDAO.commanMethod(txnId,servletResponse);
					servletResponse.setStatus(HttpStatus.SC_OK);
			}
			responsesDTO.setTransactionId(txnId);	
			} catch (Exception ex) {
			ex.printStackTrace();
		}

		return responsesDTO;
	}

}
