/***********************************************************************
 *
 * 
 *
 * @file        Application.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.quartzjob;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.usee.sky.quartzjob.conf.QuartzConfiguration;
import com.usee.sky.quartzjob.job.SampleJob;

/**
 * @author jet
 *
 */
// @Configuration
// @Import(ApplicationConfiguration.class)
public class Application
{

	public static void main(String[] args)
	{

//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"quartz.xml");
//		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
//		RunMeJob job = context.getBean(RunMeJob.class);
//		job.setCronExpression("0/5 * * * * ?");
//		job.startup();
		
		ApplicationContext context = new AnnotationConfigApplicationContext(QuartzConfiguration.class);
        SampleJob job = context.getBean(SampleJob.class);
        job.setJobName("SampleJob");
        job.setJobGroupName("SampleJobGroup");
        job.setTriggerName("SampleTrigger");
        job.setTriggerGroupName("SampleTriggerGroup");
        job.setCronExpression("0/5 * * * * ?");
        job.start();
	}
}
