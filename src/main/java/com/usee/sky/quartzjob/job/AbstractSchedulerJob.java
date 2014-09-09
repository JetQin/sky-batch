///***********************************************************************
// *
// * 
// *
// * @file        AbstractSchedulerJob.java
// *
// * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
// * @creator     JetQin <br/>
// * @create-time 2014年9月9日
// *
// *
// ***********************************************************************/
//package com.usee.sky.quartzjob.job;
//
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.quartz.JobDetail;
//import org.quartz.Trigger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailBean;
//import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.scheduling.quartz.SimpleTriggerBean;
//import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
///**
// * @author jet
// *
// */
//@Component
//public abstract class AbstractSchedulerJob extends QuartzJobBean
//{
//	protected static final Log LOG = LogFactory
//			.getLog(AbstractSchedulerJob.class);
//
//	@Autowired
//	@Qualifier("schedulerFactory")
//	public SchedulerFactoryBean schedulerFactory;
//
//	private long startDelay;
//
//	private long repeatInterval;
//
//	private int repeatCount;
//
//	private String cronExpression;
//
//
//	protected Trigger[] getCronTrigger(String cronExpression)
//	{
//		LOG.info("****Get CronTrigger****");
//		CronTriggerFactoryBean cronTriggerFactory = new CronTriggerFactoryBean();
//		cronTriggerFactory.setCronExpression(getCronExpression());
//		cronTriggerFactory.setJobDetail(getJobDetail());
//		return new Trigger[]
//		{
//			cronTriggerFactory.getObject()
//		};
//	};
//
//	protected Trigger[] getSimpleTriggers()
//	{
//		LOG.info("****Get SimpleTrigger****");
//		SimpleTriggerFactoryBean simpleTriggerFactory = new SimpleTriggerFactoryBean();
//		simpleTriggerFactory.setStartDelay(getStartDelay());
//		simpleTriggerFactory.setRepeatInterval(getRepeatInterval());
//		;
//		simpleTriggerFactory.setRepeatCount(getRepeatCount());
//		return new Trigger[]
//		{
//			simpleTriggerFactory.getObject()
//		};
//	};
//
//	protected abstract JobDetail getJobDetail();
//
//	public void startup()
//	{
//		LOG.info("****Start Up****");
//		schedulerFactory.setTriggers(getCronTrigger(cronExpression));
//		schedulerFactory.setJobDetails(getJobDetail());
//		schedulerFactory.start();
//	}
//
//	public void setCronExpression(String cronExpression)
//	{
//		this.cronExpression = cronExpression;
//	}
//	
//	public String getCronExpression()
//	{
//		return cronExpression;
//	}
//
//	public void setStartDelay(long startDelay)
//	{
//		this.startDelay = startDelay;
//	}
//
//	public long getStartDelay()
//	{
//		return startDelay;
//	}
//
//	public void setRepeatInterval(long repeatInterval)
//	{
//		this.repeatInterval = repeatInterval;
//	}
//
//	public long getRepeatInterval()
//	{
//		return repeatInterval;
//	}
//
//	public void setRepeatCount(int repeatCount)
//	{
//		this.repeatCount = repeatCount;
//	}
//
//	public int getRepeatCount()
//	{
//		return repeatCount;
//	}
//
//}
