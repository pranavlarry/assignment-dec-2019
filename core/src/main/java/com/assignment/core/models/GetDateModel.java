package com.assignment.core.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class GetDateModel extends WCMUsePojo {
	String formatedDate;
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		String date =get("date",String.class);
		int month = Integer.parseInt(date.substring(5,7));
		int day = Integer.parseInt(date.substring(8,10));
		formatedDate = formatedDateNumber(month,day);
		
	}
	
	private String formatedDateNumber(int month,int day) {
		String formatedDate= null;
        switch (month) {
	        case 1:  formatedDate = "Jan ";       break;
	        case 2:  formatedDate = "Feb ";      break;
	        case 3:  formatedDate = "Mar ";         break;
	        case 4:  formatedDate = "Apr ";         break;
	        case 5:  formatedDate = "May ";           break;
	        case 6:  formatedDate = "Jun ";          break;
	        case 7:  formatedDate = "Jul ";          break;
	        case 8:  formatedDate = "Aug ";        break;
	        case 9:  formatedDate = "Sep ";     break;
	        case 10: formatedDate = "Oct ";       break;
	        case 11: formatedDate = "Nov ";      break;
	        case 12: formatedDate = "Dec ";      break;
	        default: formatedDate = "Invalid month "; break;
        }
        formatedDate+=day;
        log.debug(formatedDate);
		return formatedDate;
		
	}

	public String getFormatedDate() {
		return formatedDate;
	}

	
}
