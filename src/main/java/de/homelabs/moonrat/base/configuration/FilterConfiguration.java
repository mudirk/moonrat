package de.homelabs.moonrat.base.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import de.homelabs.moonrat.base.filter.DomainFilter;

@Configuration
public class FilterConfiguration implements ServletContextAware {

	@Autowired
	private DomainFilter domainFilter;
	
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Bean
	public List<FilterRegistration> initFilter(){
		List<FilterRegistration> filterRegistration = new ArrayList<FilterRegistration>();
		
		//Domain Filter
		FilterRegistration domainFilterRegistration = servletContext.addFilter("domainFilter", domainFilter);
		domainFilterRegistration.addMappingForUrlPatterns(null, false, "/*");
		filterRegistration.add(domainFilterRegistration);
		
		//return
		return filterRegistration;
	}
}
