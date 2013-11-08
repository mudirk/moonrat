package de.homelabs.moonrat.base.project.dao;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jndi.JndiObjectFactoryBean;

import de.homelabs.moonrat.base.project.domain.ProjectDataSource;

public class ProjectDataSourceFactory {

	public static DataSource getDataSource(ProjectDataSource projectDataSource) {
		switch (projectDataSource.getType()) {
		case JDBC:
			return initJDBCDataSource(projectDataSource);
		case JNDI:
			return initJNDIDataSource(projectDataSource);
		default:
			return null;
		}
	}

	private static DataSource initJDBCDataSource(
			ProjectDataSource projectDataSource) {
		return null;
	}

	public static DataSource initJNDIDataSource(
			ProjectDataSource projectDataSource) {
		JndiObjectFactoryBean jndiObjectFactoryBean1 = new JndiObjectFactoryBean();
		jndiObjectFactoryBean1.setJndiName(projectDataSource.getUrl());
		try {
			jndiObjectFactoryBean1.afterPropertiesSet();
		} catch (IllegalArgumentException | NamingException e) {
			e.printStackTrace();
			//TODO: Log
			return null;
		}
		return (DataSource) jndiObjectFactoryBean1.getObject();

	}
}
