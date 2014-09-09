package com.usee.sky.quartzjob.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import static org.quartz.JobBuilder.*;

@Component
public class SampleJob extends AbstractJob implements Job
{
    
	public void execute(JobExecutionContext context)
			throws JobExecutionException
	{
		System.out.println("Hello World! - " + new Date());
	}

	@Override
	public JobDetail getJobDetail()
	{
		return newJob(this.getClass()).withIdentity(getJobName(),getJobGroupName()).build();
	}

}
