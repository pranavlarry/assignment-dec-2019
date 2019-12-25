package com.assignment.core.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.assignment.core.models.ApplicationForm;
import com.assignment.core.service.MySqlGetDataService;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths="+ "/bin/MySql",
        "sling.servlet.extensions=" + "txt"
})
public class GetDataSqlServlet extends SlingAllMethodsServlet {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 */
	private ArrayList<ApplicationForm> applicationForm;
	@Reference
	private MySqlGetDataService mySqlGetDataService;
	
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
	 String date=req.getParameter("date");
	 applicationForm=new ArrayList<>();
	 JSONObject obj=new JSONObject();
	 if(date==null) {
		 applicationForm=mySqlGetDataService.getData("SELECT * FROM applicationform.application");
		 try {
			obj.put("data",applicationForm);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 resp.setContentType("application/json");
	 resp.setCharacterEncoding("utf-8");
	 resp.getWriter().write(obj.toString());
 }
	@Override
    protected void doPost(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
		
	 String[] id= req.getParameterValues("id");
	 String[] status=req.getParameterValues("status");
	 log.debug("id-->"+id+" status-->"+status);
	 mySqlGetDataService.setStatus(id,status);
	 //LOGGER.debug(data);
 }

}
