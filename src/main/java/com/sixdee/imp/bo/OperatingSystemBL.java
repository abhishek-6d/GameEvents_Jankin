package com.sixdee.imp.bo;

import java.util.Map;

import com.sixdee.dao.TableDetailsDAO;
import com.sixdee.dtos.OsVersionReponse;

public class OperatingSystemBL {

	public OsVersionReponse buildProses(String phoneNumber, Map<String, String> headers) {
		String txnId=null;
		TableDetailsDAO tableDetailsDAO=null;
		OsVersionReponse osVersionReponse=null;
		try {
			if (phoneNumber != null && headers != null) {
				for (String key : headers.keySet()) {
					if (key.equalsIgnoreCase("X_CORRELATION_ID"))
						txnId = headers.get(key);
				}
				
				osVersionReponse=new OsVersionReponse();
				osVersionReponse.setTimeStamp(txnId);
				osVersionReponse.setTransactionId(txnId);
				osVersionReponse.setOsName(System.getProperty("os.name"));
				osVersionReponse.setOsversion(System.getProperty("os.version"));
				osVersionReponse.setOsArch(System.getProperty("os.arch"));
				
			
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return osVersionReponse;
	}

}
