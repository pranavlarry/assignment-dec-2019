package com.assignment.core.serviceImpl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.assignment.core.models.ApplicationForm;
import com.assignment.core.service.MySqlGetDataService;
import com.assignment.core.service.SendEmailService;
import com.assignment.core.service.SqlConnectionService;

@Component(service=MySqlGetDataService.class,immediate=true)
public class MySqlGetDataServiceImpl implements MySqlGetDataService {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Reference
	private SqlConnectionService sqlConnectionService;
	
	@Reference
	private SendEmailService sendEmailService;
	
	@Override
    public void insert(String firstName,String lastName,String nationality,String gender,String age,String gotSeaLegs,String partOfOurTeam,String friendOrRelativeWorkingWithUs,String areasOfInterest,String learnAboutThisJob){
        
        //Simply write out the values that are posted from the AEM form to the AEM log file
        //log.info("DB Data posted from an AEM adaptive form - customer_ID: "+customer_ID +" customer_Name: "+customer_Name +" customer_Shipping_Address: "+customer_Shipping_Address +" customer_State "+customer_State) ;
         
        Connection c = null;
         
        try {
                          
              // Create a Connection object
        	try {
              c =  sqlConnectionService.getConnection("LariMySql");
        	}catch (Exception e) {
              	 StringWriter sw = new StringWriter();
                 e.printStackTrace(new PrintWriter(sw));
                 String exceptionAsString = sw.toString();
                 //e.printStackTrace(); 
                 log.debug("now what---->"+exceptionAsString);
                 }
                   
               //Use prepared statements to protected against SQL injection attacks
               PreparedStatement ps = null; 
                             
               log.debug(firstName+"---"+lastName+"---"+nationality+"---"+gender+"---"+age+"---"+gotSeaLegs+"---"+partOfOurTeam+"---"+friendOrRelativeWorkingWithUs+"---"+areasOfInterest+"---"+learnAboutThisJob);
               String insert = "INSERT INTO applicationform.application(firstName,lastName,nationality,gender,age,gotSeaLegs,partOfOurTeam,friendOrRelativeWorkingWithUs,areasOfInterest,learnAboutThisJob,status) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
               ps = c.prepareStatement(insert);
                 
               ps.setString(1,firstName); 
               ps.setString(2, lastName);
               ps.setString(3, nationality);
               ps.setString(4, gender);
               ps.setString(5, age);
               ps.setString(6, gotSeaLegs);
               ps.setString(7,partOfOurTeam);
               ps.setString(8,friendOrRelativeWorkingWithUs);
         	   ps.setString(9,areasOfInterest);
               ps.setString(10,learnAboutThisJob);
               ps.setString(11, "new");
               ps.execute();
               
               String msg= "New Application Alert: \n"+
            		   "First Name: "+firstName +"\n"+
            		   "Last Name: "+lastName  +"\n" +
            		   "Nationality: "+ nationality  +"\n"+
            		   "Gender: "+ gender  +"\n"+
            		   "You've worked on board ships before: "+ gotSeaLegs +"\n" +
		               "Ever been a part of Our Team: "+ partOfOurTeam +"\n"+
		               "Friend or relative working with us: "+ friendOrRelativeWorkingWithUs +"\n"+
		               "Area of Interest: "+ areasOfInterest +"\n"+
		               "How did you learn about this job: "+ learnAboutThisJob +"\n";
               sendEmailService.sendMail(msg);
        }
        catch (Exception e) {
       	 StringWriter sw = new StringWriter();
         e.printStackTrace(new PrintWriter(sw));
         String exceptionAsString = sw.toString();
         //e.printStackTrace(); 
         log.debug("1---->"+exceptionAsString);
         }
        finally {
			try
			{
			   c.close();
			}
            
			catch (SQLException e) {
				 StringWriter sw = new StringWriter();
				 e.printStackTrace(new PrintWriter(sw));
				 String exceptionAsString = sw.toString();
				 //e.printStackTrace(); 
				 log.debug("2---->"+exceptionAsString);
			}
        }
        
  }
	
	
	
	@Override
	public ArrayList<ApplicationForm> getData(String query) {
		// TODO Auto-generated method stub
		ArrayList<ApplicationForm> applicationForm=new ArrayList<>();
		Connection c = null;
		try {
			try {
	            c =  sqlConnectionService.getConnection("LariMySql");
	      	}catch (Exception e) {
	            	 StringWriter sw = new StringWriter();
	               e.printStackTrace(new PrintWriter(sw));
	               String exceptionAsString = sw.toString();
	               //e.printStackTrace(); 
	               log.debug("now what---->"+exceptionAsString);
	               }
			ResultSet rs = null;

	        PreparedStatement pstmt = null;
			pstmt = c.prepareStatement(query);
	         rs = pstmt.executeQuery();
		 
			while (rs.next()) 
			 {
				 applicationForm.add(new ApplicationForm(rs.getDate(1),rs.getTime(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getInt(13)));
			 }

		}catch (Exception e) {
	       	 StringWriter sw = new StringWriter();
	         e.printStackTrace(new PrintWriter(sw));
	         String exceptionAsString = sw.toString();
	         //e.printStackTrace(); 
	         log.debug("now what---->"+exceptionAsString);
		}
        finally {
            try
            {
              c.close();
            }
              
              catch (SQLException e) {
             	 StringWriter sw = new StringWriter();
               e.printStackTrace(new PrintWriter(sw));
               String exceptionAsString = sw.toString();
               //e.printStackTrace(); 
               log.debug("2---->"+exceptionAsString);
              }
      }
		
	   Collections.sort(applicationForm, new Comparator<ApplicationForm>() {
			@Override
			public int compare(ApplicationForm a1, ApplicationForm a2) {
				// TODO Auto-generated method stub
				return a2.getDate().compareTo(a1.getDate());
			}
        });
		
		return applicationForm;
		
		
	}
	
	

	@Override
	public void setStatus(String[] id, String[] status) {
		// TODO Auto-generated method stub
//		UPDATE table_name SET column1 = value1, column2 = value2,... 
//				WHERE condition;
		Connection c = null;
		try {
			try {
	            c =  sqlConnectionService.getConnection("LariMySql");
	      	}catch (Exception e) {
	            	 StringWriter sw = new StringWriter();
	               e.printStackTrace(new PrintWriter(sw));
	               String exceptionAsString = sw.toString();
	               //e.printStackTrace(); 
	               log.debug("now what---->"+exceptionAsString);
	               }

	        PreparedStatement pstmt = null;
	        String query = null;
	        for(int i=0;i<id.length;i++) {
	        	if(query != null) {
	        		
	        		query+="UPDATE applicationform.application SET status='"+status[i]+"' WHERE id = '"+id[i]+"';";
	        	}
	        	else {
	        		query ="UPDATE applicationform.application SET status='"+status[i]+"' WHERE id = '"+id[i]+"';";
	        	}
	        }
	        log.debug(query);
	        pstmt = c.prepareStatement(query);
	        pstmt.executeUpdate();
		
		
		}catch (Exception e) {
	       	 StringWriter sw = new StringWriter();
	         e.printStackTrace(new PrintWriter(sw));
	         String exceptionAsString = sw.toString();
	         //e.printStackTrace(); 
	         log.debug("now what---->"+exceptionAsString);
         }
	    finally {
            try
            {
              c.close();
            }
              
              catch (SQLException e) {
             	 StringWriter sw = new StringWriter();
               e.printStackTrace(new PrintWriter(sw));
               String exceptionAsString = sw.toString();
               //e.printStackTrace(); 
               log.debug("2---->"+exceptionAsString);
              }
      }
	
	

	}
	
	
}
