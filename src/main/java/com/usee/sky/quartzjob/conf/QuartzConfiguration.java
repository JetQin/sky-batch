package com.usee.sky.quartzjob.conf;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Component;

import com.usee.sky.quartzjob.util.LaunchPool;

@Component
public class QuartzConfiguration
{

	private static final Log LOG = LogFactory.getLog(QuartzConfiguration.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-mm-dd hh:mm:ss");

	// @Scheduled(cron = "0/5 * * * * *")
	public void scheduleJob()
	{
		LOG.info("[SCHEDULE JOB]" + dateFormat.format(new Date()));
		Job messageJob = LaunchPool.getJob("messageJob");
		Job peopleJob = LaunchPool.getJob("job1");

		LaunchPool.startJob(peopleJob, new JobParameters());
		LaunchPool.startJob(messageJob, new JobParameters());

	}

}
