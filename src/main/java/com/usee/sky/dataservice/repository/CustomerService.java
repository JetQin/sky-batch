/***********************************************************************
 *
 * 
 *
 * @file        CustomerService.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.dataservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.usee.sky.model.Customer;

/**
 * @author jet
 *
 */
@Repository
@Transactional(readOnly = true)
public class CustomerService
{
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CustomerRepository repository;

	public Page<Customer> findAll(Pageable pageable)
	{
		return repository.findAll(pageable);
	}

	@Transactional
	public Customer save(Customer customer)
	{
		return repository.save(customer);
	}

	public Page<Customer> findByLastname(String lastname, Pageable pageable)
	{
		return repository.findByLastname(lastname, pageable);
	}
}
