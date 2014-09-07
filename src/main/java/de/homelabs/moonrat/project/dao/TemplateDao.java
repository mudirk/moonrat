package de.homelabs.moonrat.project.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.homelabs.moonrat.project.domain.Template;

@Repository
@Transactional("projectTransactionManager")
public class TemplateDao {

	@Autowired
	@Qualifier("projectSessionFactoryBean")
	SessionFactory sessionFactory;
	
	public Template getBaseTemplate(){
		return (Template) sessionFactory.getCurrentSession().createQuery("from Template where referenzId = 0").uniqueResult();
	}
}
