
package com.sixdee.imp.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sixdee.dao.TableDetailsDAO;
import com.sixdee.dto.ProductDetailsDTO;
import com.sixdee.dtos.AppPurchaseResponse;
import com.sixdee.dtos.ProductDetailsResponse;



public class AppPurchaseBL {

	public AppPurchaseResponse buildProses(String phoneNumber, Map<String, String> headers) {
		String channel=null;
		String txnId=null;
		List<ProductDetailsDTO> productDetailsDTOs=null;
		TableDetailsDAO tableDetailsDAO=null;
		AppPurchaseResponse responsesDTO=null;
		ProductDetailsResponse productresponse=null;
		ArrayList<ProductDetailsResponse>productlist=new ArrayList<ProductDetailsResponse>();
		try {
			
			tableDetailsDAO=new TableDetailsDAO();
			
			if (phoneNumber != null && headers != null) {
				for (String key : headers.keySet()) {
					if (key.equalsIgnoreCase("X_CORRELATION_ID"))
						txnId = headers.get(key);
					if (key.equalsIgnoreCase("CHANNEL"))
						channel=headers.get(key);
				
				}
				productDetailsDTOs=tableDetailsDAO.getproductDetails(phoneNumber,txnId);
				if(productDetailsDTOs!=null && productDetailsDTOs.size()>0) {
					responsesDTO=new AppPurchaseResponse();
					responsesDTO.setRespCode("SC000");
					responsesDTO.setRespDesc("SUCCESS");
					responsesDTO.setTransactionId(txnId);
					responsesDTO.setTimeStamp(String.valueOf(System.currentTimeMillis()));
					
					for(ProductDetailsDTO productDetailsDTO:productDetailsDTOs) {
						productresponse=new ProductDetailsResponse();
						productresponse.setProductId(productDetailsDTO.getProductId());
						productresponse.setProcuctName(productDetailsDTO.getProcuctName());
						productresponse.setCurrency(productDetailsDTO.getCurrency());
						productresponse.setPrice(productDetailsDTO.getPrice());
						productresponse.setQuantity(productDetailsDTO.getQuantity());
						
						productlist.add(productresponse);
					}
					
					responsesDTO.setProductResponse(productlist);
				}else {
					responsesDTO=new AppPurchaseResponse();
					responsesDTO.setRespCode("SC0001");
					responsesDTO.setRespDesc("FAILURE");
					responsesDTO.setTransactionId(txnId);
					responsesDTO.setTimeStamp(String.valueOf(System.currentTimeMillis()));
					
					responsesDTO.setProductResponse(productlist);
					
				}
				
				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		return responsesDTO;
	}

}
