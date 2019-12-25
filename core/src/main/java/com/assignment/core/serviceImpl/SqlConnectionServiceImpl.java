package com.assignment.core.serviceImpl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assignment.core.service.SqlConnectionService;
import com.day.commons.datasource.poolservice.DataSourcePool;

@Component(service=SqlConnectionService.class,immediate=true)
public class SqlConnectionServiceImpl implements SqlConnectionService{

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
    
   @Reference
   private DataSourcePool source;
   
	@Override
	public Connection getConnection(String dataSourceName) {
		DataSource dataSource = null;
        Connection con = null;
        try
        {
            //Inject the DataSourcePool right here! 
            dataSource = (DataSource) source.getDataSource(dataSourceName);
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
