/***********************************************************************
 *
 * 
 *
 * @file        AbstractBatchJob.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月9日
 *
 *
 ***********************************************************************/
package com.usee.sky.batch.job;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jet
 *
 */
@Component
public abstract class AbstractBatchJob
{
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
}
