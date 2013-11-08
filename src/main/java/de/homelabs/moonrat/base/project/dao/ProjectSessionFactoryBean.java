package de.homelabs.moonrat.base.project.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import de.homelabs.moonrat.base.project.domain.Project;
import de.homelabs.moonrat.base.project.domain.ProjectDataSource;
import de.homelabs.moonrat.base.project.service.ProjectManager;

public class ProjectSessionFactoryBean extends LocalSessionFactoryBean {
	// vars
	private static final String packagesToScan = "de.homelabs.moonrat.project";
	private Map<Object, Object> dataSources = new HashMap<Object, Object>();
	private Properties hibernateProperties = new Properties();
	private ProjectRoutingDataSource projectRoutingDataSource = new ProjectRoutingDataSource();
	private DataSource defaultTargetDataSource = null;
	private ProjectManager projectManager;
	private ApplicationContext context;
	
	public ProjectSessionFactoryBean(ProjectManager projectManager, ApplicationContext context) {
		setPackagesToScan(packagesToScan);
		setHibernateProperties(hibernateProperties);
		this.projectManager = projectManager;
		this.context = context;
	}

	public void initProjects() {
		// load projects, get database infos and init datasource
		for (Project project : projectManager.getProjects()) {
			// should only be one
			for (ProjectDataSource projectDataSource : project
					.getProjectDataSource()) {
				// create datasource
				DataSource entityDataSource = ProjectDataSourceFactory
						.getDataSource(projectDataSource);
				// use project.id as key
				// @see DomainFilter
				dataSources.put(project.getId(), entityDataSource);
				
				//set minimum one default datasource
				//TODO: perhaps a dummy or core datasource?
				if (defaultTargetDataSource == null)
					defaultTargetDataSource = entityDataSource;
			}
		}

		// combine
		projectRoutingDataSource.setTargetDataSources(dataSources);
		projectRoutingDataSource.setDefaultTargetDataSource(defaultTargetDataSource);
		projectRoutingDataSource.afterPropertiesSet();
		projectRoutingDataSource.setApplicationContext(context);
		
		// set to sessionFactory
		setDataSource(projectRoutingDataSource);
		try {
			afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
			//log
		}
	}
}
