package de.homelabs.moonrat.base.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.homelabs.moonrat.base.project.dao.ProjectSessionFactoryBean;
import de.homelabs.moonrat.base.project.service.ProjectManager;

@Configuration
public class ProjectConfiguration {

	@Autowired
	ProjectManager projectManager;
	
	@Autowired
	ApplicationContext context;
	
	@Bean(name="projectSessionFactoryBean")
	public ProjectSessionFactoryBean createProjectSession(){
		ProjectSessionFactoryBean psfb = new ProjectSessionFactoryBean(projectManager, context);
		psfb.initProjects();
		return psfb;
	}
}
