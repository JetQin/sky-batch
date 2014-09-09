///***********************************************************************
// *
// * 
// *
// * @file        RunMeJob.java
// *
// * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
// * @creator     JetQin <br/>
// * @create-time 2014年9月8日
// *
// *
// ***********************************************************************/
//package com.usee.sky.quartzjob.job;
//
//import java.util.HashMap;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobDataMap;
//import org.quartz.JobDetail;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.impl.JobDetailImpl;
//import org.springframework.scheduling.quartz.CronTriggerBean;
//import org.springframework.scheduling.quartz.JobDetailBean;
//import org.springframework.stereotype.Component;
//
///**
// * @author jet
// *
// */
//@Component
//public class RunMeJob extends AbstractSchedulerJob
//{
//
//	private JobDataMap parameters ;
//
//
//	private RunMeTask runMeTask;
//
//	public void setRunMeTask(RunMeTask runMeTask)
//	{
//		this.runMeTask = runMeTask;
//	}
//
//
//	@Override
//	protected JobDetail getJobDetail()
//	{
//		LOG.info("****Get JobDetail****");
//		JobDetailImpl jobDetail = new JobDetailImpl();
//		jobDetail.setJobClass(RunMeJob.class);
//		jobDetail.setJobDataMap(parameters);
//		return jobDetail;
//	}
//	
//	public JobDataMap getParameters()
//	{
//		return parameters;
//	}
//	
//	
//	public void setParameters(JobDataMap parameters)
//	{
//		this.parameters = parameters;
//	}
//
//
//	@Override
//	protected void executeInternal(JobExecutionContext context)
//			throws JobExecutionException
//	{
//		LOG.info("****Run JOB****");
//		runMeTask.print();
//	}
//
//}
