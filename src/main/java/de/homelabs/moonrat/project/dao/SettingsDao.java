package de.homelabs.moonrat.project.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.homelabs.collection.CollectionUtils;
import de.homelabs.moonrat.project.domain.Settings;

@Repository
@Transactional("projectTransactionManager")
public class SettingsDao {

	@Autowired
	@Qualifier("projectSessionFactoryBean")
	SessionFactory sessionFactory;
	
	public List<Settings> getSettings(){
		return CollectionUtils.convertToCheckedList(Settings.class, sessionFactory.getCurrentSession().createQuery("from Settings").list()); 
	}
}
