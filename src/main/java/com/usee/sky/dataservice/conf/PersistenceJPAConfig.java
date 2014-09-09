/***********************************************************************
 *
 * 
 *
 * @file        PersistenceJPAConfig.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.dataservice.conf;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jet
 *
 */
@Configuration
@ComponentScan("com.usee.sky.dataservice.repository")
@PropertySource(
{ "classpath:batch.properties" })
@EnableTransactionManagement
public class PersistenceJPAConfig
{
	private static final Log logger = LogFactory.getLog(PersistenceJPAConfig.class);
	
	@Autowired
	private Environment env;

//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
//	{
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(dataSource());
//		em.setPackagesToScan(new String[]
//		{ "com.usee.sky.model" });
//
//		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		em.setJpaProperties(additionalProperties());
//
//		return em;
//	}
	

	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("batch.jdbc.driver"));
		dataSource.setUrl(env.getProperty("batch.jdbc.url"));
		dataSource.setUsername(env.getProperty("batch.jdbc.user"));
		dataSource.setPassword(env.getProperty("batch.jdbc.password"));
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		try
		{
			LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
			localContainerEntityManagerFactoryBean.setDataSource(dataSource());
			localContainerEntityManagerFactoryBean.setPackagesToScan("com.usee.sky.model");
			localContainerEntityManagerFactoryBean.setPersistenceUnitName("dw");
			HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

			jpaVendorAdapter.setGenerateDdl(false);
			jpaVendorAdapter.setShowSql(true);
			jpaVendorAdapter.setDatabasePlatform(env.getProperty("dataSource.dialect"));
			localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
			return localContainerEntityManagerFactoryBean;

		}
		catch (Exception e)
		{
			logger.error("JpaConfigurationImpl.entityManagerFactory(): " + e.getMessage());
		}
		return new LocalContainerEntityManagerFactoryBean();
	}

	@Bean
	public PlatformTransactionManager transactionManager()
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean(name="entityManager")
	public EntityManager entityManager()
	{
		EntityManager entityManager = entityManagerFactory().getObject().createEntityManager();
//		EntityManager entityManager = emf.createEntityManager();
		return entityManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}

//	Properties additionalProperties()
//	{
//		Properties properties = new Properties();
//		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));              
//		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));                      
//		properties.setProperty("hibernate.query.substitutions", env.getProperty("hibernate.query.substitutions"));
//		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));                        
//		properties.setProperty("hibernate.globally_quoted_identifiers", "true");                                  
//		                                                                                               
//		properties.setProperty("hibernate.c3p0.minPoolSize", env.getProperty("hibernate.c3p0.minPoolSize"));      
//		properties.setProperty("hibernate.c3p0.maxPoolSize", env.getProperty("hibernate.c3p0.maxPoolSize"));      
//		properties.setProperty("hibernate.c3p0.timeout", env.getProperty("hibernate.c3p0.timeout"));             
//		properties.setProperty("hibernate.c3p0.max_statement", env.getProperty("hibernate.c3p0.max_statement"));  
//		properties.setProperty("hibernate.c3p0.testConnectionOnCheckout",                                         
//				env.getProperty("hibernate.c3p0.testConnectionOnCheckout"));                           
//		return properties;
//	}
 }
