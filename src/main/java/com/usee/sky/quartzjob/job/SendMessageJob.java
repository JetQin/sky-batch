///***********************************************************************
// *
// * 
// *
// * @file        SendMessageJob.java
// *
// * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
// * @creator     JetQin <br/>
// * @create-time 2014年9月8日
// *
// *
// ***********************************************************************/
//package com.usee.sky.quartzjob.job;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.quartz.CronScheduleBuilder;
//import org.quartz.Job;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.JobKey;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
///**
// * @author jet
// *
// */
//
//@Component("sendMessageJob")
//public class SendMessageJob extends AbstractJob implements Job
//{
//
//	private static final Log LOG = LogFactory.getLog(SendMessageJob.class);
//
//
//	@Override
//	public void execute(JobExecutionContext context) throws JobExecutionException
//	{
//		LOG.info("[SendMessage to DB] " + context.getNextFireTime());
//	}
//
//	@Override
//	public JobDetail getJobDetail()
//	{
//		return JobBuilder.newJob(SendMessageJob.class).withIdentity(getJobName(), getJobGroupName()).build();
//	}
//
//	@Override
//	public Trigger getTrigger()
//	{
//		return TriggerBuilder.newTrigger().withIdentity(getTriggerName(), getTriggerGroupName())
//				.withSchedule(CronScheduleBuilder.cronSchedule(getCronExpression())).build();
//	}
//
//	@Override
//	public JobKey getJobKey()
//	{
//
//		return new JobKey(getJobName(), getJobGroupName());
//	}
//
//}
