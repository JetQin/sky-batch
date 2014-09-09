///***********************************************************************
// *
// * 
// *
// * @file        PersistenceJPAConfig.java
// *
// * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
// * @creator     JetQin <br/>
// * @create-time 2014年9月8日
// *
// *
// ***********************************************************************/
//package com.usee.sky.dataservice.conf;
//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//import javax.persistence.EntityManagerFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// * @author jet
// *
// */
//@Configuration
//@PropertySource(
//{ "classpath:batch.properties" })
//@EnableTransactionManagement
//public class PersistenceJPAConfig
//{
//
//	@Autowired
//	private Environment env;
//
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
//
//	@Bean
//	public DataSource dataSource()
//	{
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(env.getProperty("batch.jdbc.driver"));
//		dataSource.setUrl(env.getProperty("batch.jdbc.url"));
//		dataSource.setUsername(env.getProperty("batch.jdbc.user"));
//		dataSource.setPassword(env.getProperty("batch.jdbc.password"));
//		return dataSource;
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
//	{
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(emf);
//		return transactionManager;
//	}
//
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
//	{
//		return new PersistenceExceptionTranslationPostProcessor();
//	}
//
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
// }
