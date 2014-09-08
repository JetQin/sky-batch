/***********************************************************************
 *
 * 
 *
 * @file        RunMeJob.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.quartzjob.job;

import java.util.HashMap;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.stereotype.Component;

/**
 * @author jet
 *
 */
@Component
public class RunMeJob extends AbstractSchedulerJob
{

	private HashMap<String, Object> parameters = new HashMap<String, Object>();
	
	private String cronExpression;
	
	private RunMeTask runMeTask;

	public void setRunMeTask(RunMeTask runMeTask)
	{
		this.runMeTask = runMeTask;
	}

	@Override
	protected Trigger[] getTriggers()
	{
		LOG.info("****Get CronTrigger****");
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
		
		return new Trigger[]
		{ trigger };
	}

	@Override
	protected JobDetailBean getJobDetail()
	{
		LOG.info("****Get JobDetail****");
		JobDetailBean jobDetail = new JobDetailBean();
		jobDetail.setJobClass(RunMeJob.class);
		jobDetail.setJobDataAsMap(parameters);
		return jobDetail;
	}

	/**
	 * @param cronExpression the cronExpression to set
	 */
	public void setCronExpression(String cronExpression)
	{
		this.cronExpression = cronExpression;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException
	{
		runMeTask.print();
	}

}
