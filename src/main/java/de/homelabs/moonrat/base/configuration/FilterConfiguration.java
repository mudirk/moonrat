package de.homelabs.moonrat.base.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import de.homelabs.moonrat.base.filter.DomainFilter;

@Configuration
public class FilterConfiguration implements ServletContextAware {

	@Autowired
	private DomainFilter domainFilter;
	
	private ServletContext servletContext;
	
	@Autowired
	AutowireCapableBeanFactory beanFactory;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

//	@Bean
//	public List<FilterRegistration> makeSomeFilter(){
//		List<FilterRegistration> filterRegistration = new ArrayList<FilterRegistration>();
//		
//		//Domain Filter
//		FilterRegistration domainFilterRegistration = servletContext.addFilter("domainFilter", domainFilter);
//		domainFilterRegistration.addMappingForUrlPatterns(null, false, "/*");
//		filterRegistration.add(domainFilterRegistration);
//		
//		//return
//		return filterRegistration;
//	}
	
//	@Bean
//	public boolean setFilterAttributes(){
//		System.out.println(domainFilter);
//		return true;
//	}
}
