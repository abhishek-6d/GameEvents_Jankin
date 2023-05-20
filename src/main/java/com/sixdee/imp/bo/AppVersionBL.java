package com.sixdee.imp.bo;

import java.util.Map;

import com.sixdee.dao.TableDetailsDAO;
import com.sixdee.dto.AppVersionDetails;
import com.sixdee.dtos.AppVersionResponse;


public class AppVersionBL {

	public AppVersionResponse buildProses(String phoneNumber,String version, Map<String, String> headers) {
		
		AppVersionResponse appResponse=null;
		String txnId=null;
		TableDetailsDAO tableDetailsDAO=null;
		AppVersionResponse appVersionResponse=null;
		Boolean checktheversion=false;
		try {
			
			tableDetailsDAO=new TableDetailsDAO();
			if (phoneNumber != null && headers != null) {
				for (String key : headers.keySet()) {
					if (key.equalsIgnoreCase("X_CORRELATION_ID"))
						txnId = headers.get(key);
				}
				
				checktheversion=tableDetailsDAO.getappVersionDetails(phoneNumber,version,txnId);
				
				if(checktheversion) {
					
					appVersionResponse=new AppVersionResponse();
					
					appVersionResponse.setTimeStamp(String.valueOf(System.currentTimeMillis()));
					appVersionResponse.setTransactionId(txnId);
					appVersionResponse.setRespCode("SC0000");
					appVersionResponse.setRespDesc("PLEASE UPDATE THE APP");
					
				}else {
                     appVersionResponse=new AppVersionResponse();
					
					appVersionResponse.setTimeStamp(String.valueOf(System.currentTimeMillis()));
					appVersionResponse.setTransactionId(txnId);
					appVersionResponse.setRespCode("SC0001");
					appVersionResponse.setRespDesc("FAILURE");
				}
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		
		return appResponse;
	}

}
