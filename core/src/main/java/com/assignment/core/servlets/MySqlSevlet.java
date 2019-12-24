package com.assignment.core.servlets;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths="+ "/bin/MySql",
        "sling.servlet.extensions=" + "txt"
})
public class MySqlSevlet extends SlingAllMethodsServlet{

	/**
	 * 
	 */
	Logger LOGGER=LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final long serialVersionUID = 1L;

	 @Override
	    protected void doPost(final SlingHttpServletRequest req,
	            final SlingHttpServletResponse resp) throws ServletException, IOException {
		 String data=req.getParameter("param1");
		 LOGGER.debug(data);
	 }
	
}
