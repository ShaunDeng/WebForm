package com.shaunz.framework.common.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import com.shaunz.framework.common.source.service.SourceService;
import com.shaunz.framework.common.utils.DateUtil;
import com.shaunz.framework.common.utils.JavaXmlBindUtil;
import com.shaunz.framework.jaxb.functionfield.FunctionFieldsXml;
import com.shaunz.webform.web.common.HomePageGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShaunzApplicationListener implements ApplicationListener<ApplicationEvent>{
	
	@Resource
	private HomePageGenerator homePageGenerator;
	@Resource
	private SourceService sourceTableGenerator;
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		/*if(event instanceof RequestHandledEvent){
			requestHandled(event);
			return ;
		}*/
		
		if(event instanceof ContextClosedEvent) {
	        applicationClosed(event);
	        return ;
	    }
		
		if(event instanceof ContextStoppedEvent){
			applicationStoped(event);
			return ;
		}
		
		if(event instanceof ContextStartedEvent){
			dapplicationStarted(event);
			return ;
		}

	    if(event instanceof ContextRefreshedEvent){
	    	applicationRefreshed(event);
	    } 
	    
	}
	
	private void applicationRefreshed(ApplicationEvent event){
		ContextRefreshedEvent e = (ContextRefreshedEvent) event;
	    ApplicationContext appContext = e.getApplicationContext();
	    if(appContext instanceof WebApplicationContext){
	    	sourceTableGenerator.init();
	    	
	    	//Generate Home page object and put it in the servlet context when start up.
	    	WebApplicationContext ctx = (WebApplicationContext) e.getApplicationContext();
		    ServletContext servletContext = ctx.getServletContext();
		    homePageGenerator.generateHomePage(servletContext);
		    
		    //Load functionFields.xml
		    File xml;
			try {
				xml = ResourceUtils.getFile("classpath:functionFields.xml");
				if(xml.exists()){
			    	try {
			    		FunctionFieldsXml functionFieldsXml =(FunctionFieldsXml) JavaXmlBindUtil.unMarShallXml(new FileInputStream(xml), FunctionFieldsXml.class);
			    		functionFieldsXml.init();
			    		servletContext.setAttribute("functionFieldsXml", functionFieldsXml);
			    		log.info("Set functionFieldsXml into Servlet Context success!");
					} catch (FileNotFoundException e1) {
						log.error(e1.getMessage());
					} catch (JAXBException e1) {
						log.error(e1.getMessage());
					} catch (Exception e1){
						log.error(e1.getMessage());
					}
			    } else {
			    	log.warn("Did'nt find functionFields.xml, that will make some functionality not work!");
			    }
			} catch (FileNotFoundException e2) {
				log.warn("Did'nt find functionFields.xml, that will make some functionality not work!");
			}
	    }
	    
	    log.info("Application Refreshed...");
	}
	
	private void dapplicationStarted(ApplicationEvent event){
		log.info("Application Started...");
	}
	
	private void applicationStoped(ApplicationEvent event){
		log.info("Application Stoped...");
	}
	
	private void applicationClosed(ApplicationEvent event){
		log.info("Application Closed...");
	}
	
	private void requestHandled(ApplicationEvent event){
		log.info("Request Handled...");
	}

}
