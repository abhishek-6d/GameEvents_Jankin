package com.sixdee.imp.bo;

import java.util.TimeZone;

import com.sixdee.dto.TimeZoneDTO;

public class TimeZoneDetilsBL {
	

	   public TimeZoneDTO timeZone(String zoneId){
		   TimeZoneDTO timeZoneDTO=new TimeZoneDTO();
		   
		     TimeZone object=null;
		     
		     
		     if(zoneId!=null) {
		    	 object = TimeZone.getTimeZone(zoneId);
		    	 
		     }else {
		    	 object = TimeZone.getDefault();
		     }
			 
			
			   String name = object.getDisplayName();
			   String []detailString=object.getAvailableIDs();
			   
			   String str = detailString.toString();
			   timeZoneDTO.setZoneId(object.getID());
			   timeZoneDTO.setZoneDisplayName(name);
			   timeZoneDTO.setAvailableIDs(str);
			   
			return timeZoneDTO;
			   
			
	   }
	  
	
}
