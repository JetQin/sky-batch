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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.usee.sky.quartzjob.conf.ApplicationConfiguration;
import com.usee.sky.quartzjob.job.RunMeJob;

/**
 * @author jet
 *
 */
@Configuration
@Import(ApplicationConfiguration.class)
public class Application
{

	public static void main(String[] args)
	{
       ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
       RunMeJob job = context.getBean(RunMeJob.class);
       job.setCronExpression("0/5 * * * * ?");
       job.startup();
	}
}
