///***********************************************************************
// *
// * 
// *
// * @file        ApplicationConfiguration.java
// *
// * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
// * @creator     JetQin <br/>
// * @create-time 2014年9月8日
// *
// *
// ***********************************************************************/
//package com.usee.sky.quartzjob.conf;
//
//import java.util.Properties;
//
//import org.apache.commons.dbcp.BasicDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
///**
// * @author jet
// *
// */
//@Configuration
//@ComponentScan("com.usee.sky.quartzjob.job")
//@PropertySource(
//{
//		"classpath:batch.properties", "classpath:quartz.properties"
//})
//public class ApplicationConfiguration
//{
//
//	private final static String APPLICATION_CONTEXT_SCHEDULE_KEY = "ApplicationContextSchedulerContextKey";
//
//	@Autowired
//	Environment env;
//
//	@Bean
//	public BasicDataSource quartzDataSource()
//	{
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(env.getProperty("batch.jdbc.driverClassName"));
//		dataSource.setUrl(env.getProperty("batch.jdbc.url"));
//		dataSource.setUsername(env.getProperty("batch.jdbc.user"));
//		dataSource.setPassword(env.getProperty("batch.jdbc.password"));
//		return dataSource;
//	}
//
//	// @Bean(name = "scheduleFactory")
//	// public StdSchedulerFactory scheduleFactory()
//	// {
//	// StdSchedulerFactory scheduleFactory = new StdSchedulerFactory();
//	// scheduleFactory.setDataSource(quartzDataSource());
//	// scheduleFactory.setApplicationContextSchedulerContextKey(APPLICATION_CONTEXT_SCHEDULE_KEY);
//	// scheduleFactory.setQuartzProperties(quartzProperties());
//	// try
//	// {
//	// scheduleFactory.initialize(quartzProperties());
//	// }
//	// catch (SchedulerException e)
//	// {
//	// e.printStackTrace();
//	// }
//	// return scheduleFactory;
//	// }
//
//	@Bean(name="schedulerFactory")
//	public SchedulerFactoryBean schedulerFactory()
//	{
//		SchedulerFactoryBean scheduleFactory = new SchedulerFactoryBean();
//		scheduleFactory.setDataSource(quartzDataSource());
////		scheduleFactory.setApplicationContextSchedulerContextKey(APPLICATION_CONTEXT_SCHEDULE_KEY);
////		scheduleFactory.setQuartzProperties(quartzProperties());
//		return scheduleFactory;
//	}
//
//	// @Bean
//	// public JobDetailBean jobDetails()
//	// {
//	// JobDetailBean jobDetail = new JobDetailBean();
//	// // jobDetail.
//	// return jobDetail;
//	// }
//
//	// @Bean
//	// public CronTrigger trigger()
//	// {
//	// CronTrigger trigger = new CronTrigger("0/5 * * * * *");
//	// return trigger;
//	// }
//
//	Properties quartzProperties()
//	{
//		return new Properties()
//		{
//			{
////				setProperty("org.quartz.scheduler.instanceName",
////						env.getProperty("org.quartz.scheduler.instanceName"));
////				setProperty("org.quartz.scheduler.instanceId",
////						env.getProperty("org.quartz.scheduler.instanceId"));
////				setProperty("org.quartz.threadPool.class",
////						env.getProperty("org.quartz.threadPool.class"));
////				setProperty("org.quartz.threadPool.threadCount",
////						env.getProperty("org.quartz.threadPool.threadCount"));
////				setProperty("org.quartz.threadPool.threadPriority",
////						env.getProperty("org.quartz.threadPool.threadPriority"));
////
////				setProperty("org.quartz.jobStore.class",
////						env.getProperty("org.quartz.jobStore.class"));
////				setProperty(
////						"org.quartz.jobStore.driverDelegateClass",
////						env.getProperty("org.quartz.jobStore.driverDelegateClass"));
////				setProperty(
////						"org.quartz.jobStore.selectWithLockSQL",
////						env.getProperty("org.quartz.jobStore.selectWithLockSQL"));
////				setProperty("org.quartz.jobStore.tablePrefix",
////						env.getProperty("org.quartz.jobStore.tablePrefix"));
////				setProperty("org.quartz.jobStore.isClustered",
////						env.getProperty("org.quartz.jobStore.isClustered"));
////				setProperty(
////						"org.quartz.jobStore.clusterCheckinInterval",
////						env.getProperty("org.quartz.jobStore.clusterCheckinInterval"));
////				setProperty("org.quartz.jobStore.misfireThreshold",
////						env.getProperty("org.quartz.jobStore.misfireThreshold"));
////
////				setProperty("org.quartz.jobStore.useProperties",
////						env.getProperty("org.quartz.jobStore.useProperties"));
////				setProperty("org.quartz.jobStore.dataSource",
////						env.getProperty("org.quartz.jobStore.dataSource"));
////				setProperty("org.quartz.jobStore.tablePrefix",
////						env.getProperty("org.quartz.jobStore.tablePrefix"));
//
//				// #============================================================================
//				// # Configure Datasources 配置数据源
//				// #============================================================================
////				setProperty("org.quartz.dataSource.myDS.driver",
////						env.getProperty("org.quartz.dataSource.myDS.driver"));
////				setProperty("org.quartz.dataSource.myDS.URL",
////						env.getProperty("org.quartz.dataSource.myDS.URL"));
////				setProperty("org.quartz.dataSource.myDS.user",
////						env.getProperty("org.quartz.dataSource.myDS.user"));
////				setProperty("org.quartz.dataSource.myDS.password",
////						env.getProperty("org.quartz.dataSource.myDS.password"));
//			}
//		};
//	}
//}
