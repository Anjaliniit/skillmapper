package com.niit.skillmapper.config;


import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.debug("Starting of the Method getRootConfigClasses");
		log.debug("Ending of the Method getRootConfigClasses");
		return new Class<?>[]{ApplicationContextConfig.class,CORSFilter.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.debug("Starting of the Method getServletConfigClasses");
		log.debug("Ending of the Method getServletConfigClasses");
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		log.debug("Starting of the Method getServletMappings");
		return new String[]{ "/"};
	}
	
	
	@Override
	protected Filter[] getServletFilters() {
		Filter[] singleton = { new CORSFilter() };
		return singleton;
	}
	

}
