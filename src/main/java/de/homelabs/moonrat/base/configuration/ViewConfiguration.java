package de.homelabs.moonrat.base.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * configure application view logic
 * 
 * @author D.Mueller
 *
 */
@Configuration
public class ViewConfiguration {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * configure FreeMarker as ViewResolver
	 * 
	 * @return FreeMarkerViewResolver
	 */
	@Bean
	public FreeMarkerViewResolver initFreeMarkerViewResolver(){
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setCache(true);
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".ftl");
		
		return viewResolver;
	}
	
	/**
	 * FreeMarker needs a FreeMarkerConfigurer to tell FreeMarker where to 
	 * look for .ftl templates
	 * 
	 * @return FreeMarkerConfigurer
	 */
	@Bean
	public FreeMarkerConfigurer initFreeMarkerConfigurer(){
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		
		//setup root classpath
        configurer.setTemplateLoaderPath("classpath:/de/homelabs/moonrat/pages");
        try {
			freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_SLF4J);
			logger.debug("switched Freemarker logging to slf4j");
		} catch (ClassNotFoundException e) {
			logger.error("Failed to switch Freemarker loggin: {}", e.getMessage());
		}
        return configurer;
	}
}
