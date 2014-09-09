package com.usee.sky.quartzjob.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SampleJob implements Job
{
	public void execute(JobExecutionContext context)
			throws JobExecutionException
	{
		System.out.println("Hello World! - " + new Date());
	}
}
