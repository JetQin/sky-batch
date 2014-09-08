/***********************************************************************
 *
 * 
 *
 * @file        AbstractSchedulerJob.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月9日
 *
 *
 ***********************************************************************/
package com.usee.sky.quartzjob.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author jet
 *
 */
@Component
public abstract class AbstractSchedulerJob extends QuartzJobBean
{
	protected static final Log LOG  = LogFactory.getLog(AbstractSchedulerJob.class);
	
	@Autowired
	private SchedulerFactoryBean schedulerFactory;
	
	private JobDetail jobDetail;
	
	private Trigger[] triggers;
	
	/**
	 * @return the schedulerFactory
	 */
	public SchedulerFactoryBean getSchedulerFactory()
	{
		return schedulerFactory;
	}
 
	protected abstract Trigger[] getTriggers();
	protected abstract JobDetailBean getJobDetail();
	
	public void startup()
	{
		LOG.info("****Start Up****");
		schedulerFactory.setTriggers(getTriggers());
		schedulerFactory.setJobDetails(getJobDetail());
	}
}
