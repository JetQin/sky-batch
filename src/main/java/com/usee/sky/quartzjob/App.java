package com.usee.sky.quartzjob;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.usee.sky.quartzjob.job.SampleJob;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{

		try
		{
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// JobDetail
			JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
					.withIdentity("newJob123125", "group3").build();

			// schedule start time
			Date runTime = DateBuilder.evenMinuteDate(new Date());

			// trigger init
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("newSimpleTrigger5", "group3")
					.startAt(runTime).build();

			scheduler.scheduleJob(jobDetail, trigger);

			// and start it off
			scheduler.start();

			Thread.sleep(1000L);

			scheduler.shutdown();

		} catch (InterruptedException ex)
		{
			ex.printStackTrace();
		} catch (SchedulerException se)
		{
			se.printStackTrace();
		}
	}
}
