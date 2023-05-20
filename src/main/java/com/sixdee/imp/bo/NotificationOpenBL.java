package com.sixdee.imp.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sixdee.dao.TableDetailsDAO;
import com.sixdee.dto.MessageDetailsDTO;
import com.sixdee.dto.MsgResponseDetails;
import com.sixdee.dtos.NotificationResponse;


public class NotificationOpenBL {

	public NotificationResponse buildProses(String phoneNumber, Map<String, String> headers) {
		String txnId=null;
		List<MessageDetailsDTO> msgDetails=null;
		TableDetailsDAO tableDetailsDAO=null;
		NotificationResponse responsesDTO=null;
		MsgResponseDetails msgDetailsresponse=null;
		ArrayList<MsgResponseDetails>productlist=new ArrayList<MsgResponseDetails>();
		try {
			tableDetailsDAO=new TableDetailsDAO();
			
			
			if (phoneNumber != null && headers != null) {
				for (String key : headers.keySet()) {
					if (key.equalsIgnoreCase("X_CORRELATION_ID"))
						txnId = headers.get(key);
				}
				
				msgDetails=tableDetailsDAO.getMessageDetails(phoneNumber,txnId);
				
				if(msgDetails!=null && msgDetails.size()>0) {
					
					responsesDTO=new NotificationResponse();
					responsesDTO.setRespCode("SC000");
					responsesDTO.setRespDesc("SUCCESS");
					responsesDTO.setTransactionId(txnId);
					responsesDTO.setTimeStamp(String.valueOf(System.currentTimeMillis()));
					
					for(MessageDetailsDTO msgDetailsdto:msgDetails) {
						
						msgDetailsresponse=new MsgResponseDetails();
						
						msgDetailsresponse.setMessageName(msgDetailsdto.getMsgName());
						msgDetailsresponse.setMesssageId(msgDetailsdto.getMsgId());
						
						productlist.add(msgDetailsresponse);
						
					}
					
					responsesDTO.setMessageDetails(productlist);
				}else {
					responsesDTO=new NotificationResponse();
					responsesDTO.setRespCode("SC0001");
					responsesDTO.setRespDesc("FAILURE");
					responsesDTO.setTransactionId(txnId);
					responsesDTO.setTimeStamp(String.valueOf(System.currentTimeMillis()));
					
				}
				
				
				
			}
			
			
			
			
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		
		
		
		return responsesDTO;
	}

}
