/***********************************************************************
 *
 * 
 *
 * @file        ServiceConfiguration.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.dataservice.conf;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author jet
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(
{
	"classpath:batch.properties"
})
@ComponentScan(
{
	"com.usee.sky.dataservice"
})
public class ServiceConfiguration
{

	@Autowired
	private Environment env;

	@Bean(name = "hibernateSessionFactory")
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(restDataSource());
		sessionFactory.setPackagesToScan(new String[]
		{
			"com.usee.sky.model"
		});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public BasicDataSource restDataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env
				.getProperty("batch.jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("batch.jdbc.url"));
		dataSource.setUsername(env.getProperty("batch.jdbc.user"));
		dataSource.setPassword(env.getProperty("batch.jdbc.password"));
		return dataSource;
	}

	// @Bean(name="datasourceTransactionManager")
	// public DataSourceTransactionManager transactionManager(SessionFactory
	// sessionFactory)
	// {
	// DataSourceTransactionManager txManager = new
	// DataSourceTransactionManager();
	// txManager.setDataSource(restDataSource());
	// return txManager;
	// }

	// @Bean
	// public PlatformTransactionManager transactionManager(SessionFactory
	// sessionFactory)
	// {
	// PlatformTransactionManager txManager = new JtaTransactionManager();
	// return txManager;
	// }

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(
			SessionFactory sessionFactory)
	{
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		txManager.setDataSource(restDataSource());
		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties hibernateProperties()
	{
		return new Properties()
		{
			{
				setProperty("hibernate.hbm2ddl.auto",
						env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.dialect",
						env.getProperty("hibernate.dialect"));
				setProperty("hibernate.show_sql",
						env.getProperty("hibernate.show_sql"));
				setProperty("hibernate.query.substitutions",
						env.getProperty("hibernate.query.substitutions"));
				setProperty("hibernate.dialect",
						env.getProperty("hibernate.dialect"));
				// setProperty("hibernate.current_session_context_class",
				// env.getProperty("hibernate.current_session_context_class"));
				setProperty("hibernate.globally_quoted_identifiers", "true");

				setProperty("hibernate.c3p0.minPoolSize",
						env.getProperty("hibernate.c3p0.minPoolSize"));
				setProperty("hibernate.c3p0.maxPoolSize",
						env.getProperty("hibernate.c3p0.maxPoolSize"));
				setProperty("hibernate.c3p0.timeout",
						env.getProperty("hibernate.c3p0.timeout"));
				setProperty("hibernate.c3p0.max_statement",
						env.getProperty("hibernate.c3p0.max_statement"));
				setProperty(
						"hibernate.c3p0.testConnectionOnCheckout",
						env.getProperty("hibernate.c3p0.testConnectionOnCheckout"));

			}
		};
	}
}
