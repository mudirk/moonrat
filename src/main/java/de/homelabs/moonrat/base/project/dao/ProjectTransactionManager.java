package de.homelabs.moonrat.base.project.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Service;

@Service
public class ProjectTransactionManager extends HibernateTransactionManager {

	@Autowired
	@Resource(name="projectSessionFactoryBean")
	SessionFactory projectSessionFactory;
	
	private static final long serialVersionUID = 1L;

	public ProjectTransactionManager(){}
	
	@PostConstruct
	protected void initSessionFactory(){
		try {
			setSessionFactory(projectSessionFactory);
		} catch (Exception e) {
			e.printStackTrace();
			//TODO: log
		}
	}
}
