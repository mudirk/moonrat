package de.homelabs.moonrat.base.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class CoreDatabaseConfiguration {


	private static final String coreDataBaseName="java:comp/env/jdbc/moonrat_base";
	
	@Bean
	public JndiObjectFactoryBean getCoreDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName(coreDataBaseName);
		return jndiObjectFactoryBean;
	}

	@Bean
	@Qualifier("coreSessionFactoryBean")
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setPackagesToScan("de.homelabs.moonrat");
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.format_sql", true);
		hibernateProperties.put("hibernate.use_sql_comments", true);
		lsfb.setHibernateProperties(hibernateProperties);
		lsfb.setDataSource((DataSource)getCoreDataSource().getObject());
		return lsfb;
	}

	@Bean
	@Qualifier("coreTransactionManager")
	public HibernateTransactionManager transactionManager(){
		HibernateTransactionManager txManager= new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactoryBean().getObject());
		return txManager;
	}
}
