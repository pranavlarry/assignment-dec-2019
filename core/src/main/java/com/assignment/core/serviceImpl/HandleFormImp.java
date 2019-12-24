package com.assignment.core.serviceImpl;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assignment.core.service.HandleForm;
//Add the DataSourcePool package
import com.day.commons.datasource.poolservice.DataSourcePool;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
  
  
import java.sql.SQLException;
import javax.sql.DataSource;
 
@Component
public class HandleFormImp implements HandleForm{
     
    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
     
    @Reference
    private DataSourcePool source;
  
    //Inject the Form Data into a database! 
    @Override
    public void injestFormDataDB(String firstName,String lastName,String nationality,String gender,String age,String gotSeaLegs,String partOfOurTeam,String friendOrRelativeWorkingWithUs,String areasOfInterest,String learnAboutThisJob)
{
    	
    	log.debug("iam called");
         
        //Simply write out the values that are posted from the AEM form to the AEM log file
        //log.info("DB Data posted from an AEM adaptive form - customer_ID: "+customer_ID +" customer_Name: "+customer_Name +" customer_Shipping_Address: "+customer_Shipping_Address +" customer_State "+customer_State) ;
         
        Connection c = null;
         
        int rowCount= 0; 
        try {
                          
              // Create a Connection object
        	try {
              c =  getConnection();
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
                   
               //Use prepared statements to protected against SQL injection attacks
               PreparedStatement pstmt = null;
               PreparedStatement ps = null; 
                             
//               int pk = Integer.parseInt(customer_ID);    
               //int intZIP =Integer.parseInt(customer_ZIPCode);    
                 
//               log.info("****** THe PK IS is "+pk); 
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
 
  //Returns a connection using the configured DataSourcePool 
    private Connection getConnection()
    {
             DataSource dataSource = null;
             Connection con = null;
             try
             {
                 //Inject the DataSourcePool right here! 
                 dataSource = (DataSource) source.getDataSource("LariMySql");
                 con = dataSource.getConnection();
                 return con;
                   
               }
             catch (Exception e)
             {
            	 StringWriter sw = new StringWriter();
                 e.printStackTrace(new PrintWriter(sw));
                 String exceptionAsString = sw.toString();
                 //e.printStackTrace(); 
                 log.debug("3--->"+exceptionAsString);
             }
                 return null; 
    }
           
  }
