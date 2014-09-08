package com.usee.sky.quartzjob.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.usee.sky.batch.configuration.BatchConfiguration;

public class LaunchPool
{

	private static final ApplicationContext context = new AnnotationConfigApplicationContext(BatchConfiguration.class);

	private static final Log LOG = LogFactory.getLog(LaunchPool.class);

	private LaunchPool()
	{
	}

	public static Job getJob(String name)
	{
		return (Job) context.getBean(name);
	}

	private static JobLauncher getLaunchInstance()
	{
		return context.getBean(SimpleJobLauncher.class);
	}

	public static void startJob(Job job, JobParameters parameter)
	{
		LOG.info("************START JOB***************");

		try
		{
			getLaunchInstance().run(job, parameter);
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}
	}
}
