package com.sixdee.dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
import com.sixdee.dtos.NotificationResponse;
import com.sixdee.dtos.ResponsesDTO;

import com.sixdee.dto.TimeZoneDTO;

import com.sixdee.util.HibernateUtil;

public class TableDetailsDAO {
	private Logger logger = LogManager.getLogger(TableDetailsDAO.class);

	public boolean checkRegUser(String msisdn, String txnId) {

		Session session = null;
		String sql=null;
		long l1 = System.currentTimeMillis();
		boolean isUserRegister = false;
		logger.info(" user:{},txnID:{} ", msisdn,txnId);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			 sql="FROM UserRegisterDTO WHERE msisdn=? ";
			 Query query = session.createQuery(sql);
			 query.setParameter(0, msisdn);
		     
			
			List<UserRegisterDTO> userlist = query.list();
			logger.info(" isUserRegister:{},List:{} ", userlist,query.list().size());
			if (userlist != null && userlist.size() >= 1) {

				isUserRegister = true;

			}
			logger.info(" isUserRegister:{},List:{} ", isUserRegister,query);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			logger.info("Total Time :{} " , (System.currentTimeMillis() - l1));
		}
		return isUserRegister;

	}

	public List<ProductDetailsDTO> getproductDetails(String msisdn, String txnId) {
	
		Session session = null;
		long l1 = System.currentTimeMillis();
		// boolean isUserRegister = false;
		
		logger.info(" user:{},txnID:{} ", msisdn,txnId);
		List<ProductDetailsDTO> productDetailsDTOs =null;
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("FROM ProductDetailsDTO WHERE msisdn=:msisdn");
			query.setString("msisdn", msisdn);
			productDetailsDTOs= query.list();
			
			logger.info(" ProductDetailsList{} ", productDetailsDTOs.size());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (session != null)
				session.close();
			logger.info("Total Time :{} " , (System.currentTimeMillis() - l1));
		}
		return productDetailsDTOs;
	}
	
	public List<MessageDetailsDTO> getMessageDetails(String msisdn, String txnId) {
	
		Session session = null;
		long l1 = System.currentTimeMillis();
		// boolean isUserRegister = false;
		
		logger.info(" user:{},txnID:{} ", msisdn,txnId);
		List<MessageDetailsDTO> msgDetailsDTOs =null;
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("FROM MessageDetailsDTO WHERE msisdn=:msisdn");
			query.setString("msisdn", msisdn);
			msgDetailsDTOs= query.list();
			
			logger.info(" msgDetailsDTOs{} ", msgDetailsDTOs.size());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (session != null)
				session.close();
			logger.info("Total Time :{} " , (System.currentTimeMillis() - l1));
		}
		return msgDetailsDTOs;
	}

	public Boolean getappVersionDetails(String phoneNumber,String version ,String txnId) {
	
		Session session = null;
		long l1 = System.currentTimeMillis();
	
		
		logger.info(" user:{},txnID:{} ", phoneNumber,txnId);
		List<AppVersionDetails> appVersionDetails =null;
		String currentversion=null;
		Boolean versionChecking=false;
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("SELECT CURRENT_VERSION FROM AppVersionDetails WHERE msisdn=:msisdn");
			query.setString("msisdn", phoneNumber);
			appVersionDetails= query.list();
			logger.info(" appVersionDetails{} ", appVersionDetails.size());
			if(appVersionDetails!=null && appVersionDetails.size()>0) {
				
				currentversion=appVersionDetails.get(0).getCurrent_version();
				
			}
			
			
			if(Double.valueOf(currentversion)>Double.valueOf(version)) {
				versionChecking=true;
			}
			
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (session != null)
				session.close();
			logger.info("Total Time :{} " , (System.currentTimeMillis() - l1));
		}
		return versionChecking;
	}

	public void insertProductDetials(ProductDetailsDTO productDetailsDTO) {
		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING");
			session.save(productDetailsDTO);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	}

	public void insertNotificationMessageDetials(MessageDetailsDTO messageDetailsDTO) {
		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING");
			session.save(messageDetailsDTO);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	}

	public void insertOsVersion(OsVersionDetails osVersion) {
		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING ");
			session.save(osVersion);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	}

	public void inserViewVideo(VideoViewDetails videoViewDetails) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING ");
			session.save(videoViewDetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}
	public void insertdeviceDetails(DeviceIdDetails deviceDetails) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING ");
			session.save(deviceDetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public void insertdeviceDetails(TimeZoneDTO timeZoneDTO) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING ");
			session.save(timeZoneDTO);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public void insertdeviceDetails(RewardDetailsDTO rewardDetailsDTO) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING rewardDetailsDTO");
			session.save(rewardDetailsDTO);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public void insertdeviceDetails(GamedetailsDTO gamedetailsDTO) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING gamedetailsDTO");
			session.save(gamedetailsDTO);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public ResponsesDTO commanMethod(String txnId,HttpServletResponse servletResponse) {
		ResponsesDTO responsesDTO = new ResponsesDTO();
		responsesDTO.setRespCode("SC000");	
		responsesDTO.setRespDesc("SUCCESS");
		responsesDTO.setTimeStamp(String.valueOf(System.currentTimeMillis()));
		responsesDTO.setTransactionId(txnId);
		servletResponse.setStatus(HttpStatus.SC_OK);
		
		return responsesDTO;
	}

	public void insertdeviceDetails(ProductIddetailsDTO productIddetailsDTO) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING productIddetailsDTO");
			session.save(productIddetailsDTO);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public void insertdeviceDetails(CartDetaisDTO cartDetaisDTO) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING cartDetaisDTO");
			session.save(cartDetaisDTO);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public void insertdeviceDetails(CartWishDetailsDTO cartWishDetailsDTO) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING cartWishDetailsDTO");
			session.save(cartWishDetailsDTO);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public void insertUserDetials(UserRegisterDTO userRegisterDTO) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING userRegisterDTO");
			session.save(userRegisterDTO);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public void inserAppVersiondetails(AppVersionDetails appVersionDetails) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING appVersionDetails");
			session.save(appVersionDetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public void insertdeviceDetails(DeviceNameDetails deviceNameDetails) {

		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING deviceNameDetails");
			session.save(deviceNameDetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	}

	public void insertDismissMessageDetials(NotificationDismissDetails notificationDismissDetails) {


		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING notificationDismissDetails");
			session.save(notificationDismissDetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	
	}

	public void insersearchDetails(SearchResultdetails serchResultdetails) {


		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING serchResultdetails");
			session.save(serchResultdetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	
	}

	public void inserloctiondetails(LocationDetails locationDetails) {


		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING locationDetails");
			session.save(locationDetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	
	}

	public void insertcurrentTime(CurrentTimeDetails currentTimeDetails) {



		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING currentTimeDetails");
			session.save(currentTimeDetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	
	
		
	}

	public void insertbuttonDetails(ClickButtonDetails clickButtonDetails) {



		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING clickButtonDetails");
			session.save(clickButtonDetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	
	
		
	}

	public void insertdeviceDetails(IntentendataDetails intentendataDetails) {



		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			logger.info("=>>>>>>>>>>>>>>>>SAVING intentendataDetails");
			session.save(intentendataDetails);
			transaction.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}finally{ 
			try {
				if (session != null && session.isOpen()) {
					session.close();
					session = null;
				}

			} catch (Exception e) {
				throw e;
			}
		}
	
	
		
	
	
		
	}

	public Boolean insertListofCustomDetails(List<CustomEventDetails> customEventList) {

		int counter = 0;
		boolean flag = false;
		Integer productId = null;
		Session session=null;
		try {
			logger.info("customEventList List Size " + customEventList.size());
			session=HibernateUtil.getSessionFactory().openSession();
			long startTime = System.currentTimeMillis();
			
			if (customEventList != null && customEventList.size() > 0) {
				for (CustomEventDetails customEvents : customEventList) {
					
					session.persist(customEvents);
					logger.info(
							"Inserted into customEvents table " + customEvents.toString());
				}
				counter++;
				flag = true;
			}
			session.flush();
			long endTime = System.currentTimeMillis();
			logger.info("Time taken for Repopulating [" + (endTime - startTime) + "] Records [" + counter + "]");
		} catch (ClassCastException e) {
			e.printStackTrace();
			logger.info("No data found for flushing");
		} finally {

		}
		return flag;
	}


}
