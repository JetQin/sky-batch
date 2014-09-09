package com.usee.sky.quartzjob.conf;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.usee.sky.quartzjob.job")
public class QuartzConfiguration
{

	private static final Log LOG = LogFactory.getLog(QuartzConfiguration.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

	protected String jobName;
	
	protected String jobGroupName;
	
	protected String triggerName;
	
	protected String triggerGroupName;
	
	protected String cronExpression;
	
	// @Scheduled(cron = "0/5 * * * * *")
	// public void scheduleJob()
	// {
	// LOG.info("[SCHEDULE JOB]" + dateFormat.format(new Date()));
	// Job messageJob = LaunchPool.getJob("messageJob");
	// Job peopleJob = LaunchPool.getJob("job1");
	//
	// LaunchPool.startJob(peopleJob, new JobParameters());
	// LaunchPool.startJob(messageJob, new JobParameters());
	//
	// }
	
	@Bean(name="schedulerFactory")
	public SchedulerFactory schedulerFactory()
	{
		SchedulerFactory factory = new StdSchedulerFactory();
		return factory;
	}

	@Bean(name="scheduler")
	public Scheduler scheduler()
	{
		Scheduler scheduler = null;
		try
		{
			scheduler = schedulerFactory().getScheduler();
		}
		catch (SchedulerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scheduler;
	}
}
