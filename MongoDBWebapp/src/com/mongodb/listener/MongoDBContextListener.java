package com.mongodb.listener;

import java.io.File;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.mongodb.MongoClient;

@WebListener
public class MongoDBContextListener implements ServletContextListener {
	Logger logger = Logger.getLogger(MongoDBContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		MongoClient mongo = (MongoClient) sce.getServletContext().getAttribute("MONGO_CLIENT");
		mongo.close();
		logger.debug("MongoClient closed successfully");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext ctx = sce.getServletContext();
			MongoClient mongo = new MongoClient(ctx.getInitParameter("MONGODB_HOST"),
					Integer.parseInt(ctx.getInitParameter("MONGODB_PORT")));
			sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
			
			String log4jConfigFile = ctx.getInitParameter("log4j-config-location");
	        String fullPath = ctx.getRealPath("") + File.separator + log4jConfigFile;
	         
	        PropertyConfigurator.configure(fullPath);
	        logger.debug("MongoClient initialized successfully");
		} catch (UnknownHostException e) {
			throw new RuntimeException("MongoClient init failed");
		}
	}

}