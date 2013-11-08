package de.homelabs.moonrat.base.project.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import de.homelabs.moonrat.base.project.domain.ProjectSessionEntity;

public class ProjectRoutingDataSource extends AbstractRoutingDataSource {

	private Object defaultDataSource;
	
	private ApplicationContext context;
	
	@Override
	protected Object determineCurrentLookupKey() {
//		Object test ;
//
//		try {
//		 test = RequestContextHolder.currentRequestAttributes().getAttribute("project", RequestAttributes.SCOPE_SESSION);
//		} catch (Exception e){
//			return this.defaultDataSource;
//		}
//		//return ProjectContextHolder.getProjectType();
//		return test;
		if (context != null){
			try {
				//long pid = (long) context.getAttribute("projectId");
				ProjectSessionEntity entity = (ProjectSessionEntity) context.getBean("projectSessionEntity");
				System.out.println(entity.getId());
				return entity.getId();
			} catch (Exception e){
				return this.defaultDataSource;
			}
		} else {
			return this.defaultDataSource;
		}
	}

	@Override
	public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
		this.defaultDataSource = defaultTargetDataSource;
		super.setDefaultTargetDataSource(defaultTargetDataSource);
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.context = applicationContext;
	}


}
