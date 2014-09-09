/***********************************************************************
 *
 * 
 *
 * @file        RunMeTask.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.quartzjob.job;

import java.util.Date;

/**
 * @author jet
 *
 */
public class RunMeTask
{
	public void print()
	{
		System.out.println("[Date]=" + new Date());
	}
}
