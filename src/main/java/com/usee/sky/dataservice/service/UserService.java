/***********************************************************************
 *
 * 
 *
 * @file        UserService.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.dataservice.service;

import org.springframework.stereotype.Repository;

import com.usee.sky.model.Customer;

/**
 * @author jet
 *
 */
@Repository("userService")
public class UserService extends AbstractDataService<Customer>
{

	public UserService()
	{
		setClazz(Customer.class);
	}
}
