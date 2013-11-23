package de.homelabs.moonrat.base.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import de.homelabs.moonrat.base.filter.DomainFilter;
import de.homelabs.moonrat.base.filter.MoonratDispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {
	
	private final String webcontext = "moonrat";
	
	@Autowired
	private DomainFilter domainFilter;
	
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		//define appcontext
		AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
		root.setServletContext(servletContext);
		root.scan("de.homelabs");

		//define servlet
		Dynamic servlet = servletContext.addServlet(webcontext,	new MoonratDispatcherServlet(root));	
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
		//add filter
		FilterRegistration.Dynamic filterReg = servletContext.addFilter("domainFilter", DelegatingFilterProxy.class);
		filterReg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.ERROR, DispatcherType.REQUEST), false, "/*");
	    
		//context listener
		ContextLoaderListener ctxListener = new ContextLoaderListener(root);
		servletContext.addListener(ctxListener);
		
		//servletContext.addFilter("domainFilter", DomainFilter.class).addMappingForUrlPatterns(null, false, "/*");
//		servletContext.addFilter("domainFilter", domainFilter).addMappingForUrlPatterns(null, false, "/*");
		//filter neu
//		FilterRegistration.Dynamic springFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
//        springFilter.addMappingForUrlPatterns(null, true, "/*");
	}
}

