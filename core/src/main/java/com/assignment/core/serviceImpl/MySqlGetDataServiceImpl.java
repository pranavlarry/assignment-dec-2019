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
import java.sql.Statement;
import java.util.ArrayList;


import com.assignment.core.models.ApplicationForm;
import com.assignment.core.service.MySqlGetDataService;
import com.assignment.core.service.SqlConnectionService;

@Component(service=MySqlGetDataService.class,immediate=true)
public class MySqlGetDataServiceImpl implements MySqlGetDataService {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Reference
	private SqlConnectionService sqlConnectionService;
	
	@Override
	public ArrayList getData(String query) {
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
			Statement s = c.createStatement();
	        Statement scount = c.createStatement();

	        PreparedStatement pstmt = null;
	        PreparedStatement ps = null; 
			pstmt = c.prepareStatement(query);
	         rs = pstmt.executeQuery();
		 
			while (rs.next()) 
			 {
				 applicationForm.add(new ApplicationForm(rs.getDate(1),rs.getTime(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getInt(13)));
				 log.debug("fucking hell it worked---->"+rs.getString(2));
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
			ResultSet rs = null;
			Statement s = c.createStatement();
	        Statement scount = c.createStatement();

	        PreparedStatement pstmt = null;
	        PreparedStatement ps = null; 
	        String query = null;
	        for(int i=0;i<id.length;i++) {
	        	query+="UPDATE applicationform.application SET status='"+status[i]+"' WHERE id = '"+id[i]+"';";
	        }
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
