package com.usee.sky.startup;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.SyncTaskExecutor;

import com.usee.sky.batch.configuration.BatchConfiguration;

public class Startup
{

	private static final String RUN_MONTH_KEY = null;

	public static void main(String[] args)
	{

		ApplicationContext context = new AnnotationConfigApplicationContext(BatchConfiguration.class);
		SimpleJobLauncher launcher = context.getBean(SimpleJobLauncher.class);
		try
		{
			launcher.run((Job) context.getBean("job1"), new JobParameters());
//			launcher.run((Job) context.getBean("messageJob"), new JobParameters());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
//		SimpleJobLauncher  billlauncher = new SimpleJobLauncher();
//		billlauncher.setJobRepository((JobRepository) context.getBean("mysqlJobRepository"));
//		billlauncher.setTaskExecutor(new SyncTaskExecutor());
//		try
//		{
//			Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
//			parameters.put(RUN_MONTH_KEY, new JobParameter("2011-2"));
//			JobExecution je = launcher.run((Job) context.getBean("billingJob"), new JobParameters(parameters));
//			System.out.println(je);
//			System.out.println(je.getJobInstance());
//			System.out.println(je.getStepExecutions());
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}
}
