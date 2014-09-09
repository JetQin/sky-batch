/***********************************************************************
 *
 * 
 *
 * @file        AbstractJob.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.quartzjob.job;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * @author jet
 *
 */
@Component
public abstract class AbstractJob
{

	private static final Log LOG = LogFactory.getLog(AbstractJob.class);

	private String jobName;

	private String jobGroupName;

	private String triggerName;

	private String triggerGroupName;

	private String cronExpression;


	@Autowired
	@Qualifier("schedulerFactory")
	public SchedulerFactory scheduleFactory;

	@Autowired
	@Qualifier("scheduler")
	public Scheduler scheduler;

	protected void addScheduleJob()
	{
		try
		{
			scheduler.scheduleJob(getJobDetail(), getTrigger());
		}
		catch (SchedulerException e)
		{
			LOG.error(e.getMessage());
		}
	}

	protected void removeScheduleJob(JobKey key)
	{

		try
		{
			scheduler.deleteJob(key);
		}
		catch (SchedulerException e)
		{
			// TODO Auto-generated catch block
			LOG.error(e.getMessage());
		}
	}

	public void start()
	{
		try
		{
			addScheduleJob();
			scheduler.start();
		}
		catch (SchedulerException e)
		{
			LOG.error(e.getMessage());
		}
	}

	public void shutdown()
	{
		try
		{
			scheduler.shutdown();
		}
		catch (SchedulerException e)
		{
			LOG.error(e.getMessage());
		}
	}

	public void pause()
	{
		try
		{
			scheduler.pauseAll();
		}
		catch (SchedulerException e)
		{
			e.printStackTrace();
		}
	}

	protected void resume()
	{
		try
		{
			scheduler.resumeAll();
		}
		catch (SchedulerException e)
		{
			LOG.error(e.getMessage());
		}
	}

	public abstract JobDetail getJobDetail();

	public Trigger getTrigger(){

		return newTrigger().withIdentity(getTriggerName(), getTriggerGroupName())
		    .withSchedule(cronSchedule(getCronExpression())).build();
	};

	public JobKey getJobKey()
	{
		return new JobKey(getJobName(),getJobGroupName());
	};
	
	public String getJobName()
	{
		return jobName;
	}

	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}

	public String getJobGroupName()
	{
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName)
	{
		this.jobGroupName = jobGroupName;
	}

	public String getTriggerName()
	{
		return triggerName;
	}

	public void setTriggerName(String triggerName)
	{
		this.triggerName = triggerName;
	}

	public String getTriggerGroupName()
	{
		return triggerGroupName;
	}

	public void setTriggerGroupName(String triggerGroupName)
	{
		this.triggerGroupName = triggerGroupName;
	}

	public String getCronExpression()
	{
		return cronExpression;
	}

	public void setCronExpression(String cronExpression)
	{
		this.cronExpression = cronExpression;
	}
	
	
 }
