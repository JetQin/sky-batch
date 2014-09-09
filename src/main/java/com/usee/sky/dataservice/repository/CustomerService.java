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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
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
	@Qualifier("entityManager")
	public EntityManager entityManager;

//	@Autowired
//	@Qualifier("customerRepository")
//	public CustomerRepository customerRepository;
	
//	@Bean(name="customerRepository")
//	public CustomerRepository customerRepository()
//	{
//		CustomerRepository repository = new CustomerRepository(Customer.class,entityManager);
//		return repository;
//	}
//
//	public Page<Customer> findAll(Pageable pageable)
//	{
//		return customerRepository().findAll(pageable);
//	}
//
//	@Transactional
//	public Customer save(Customer customer)
//	{
//		return customerRepository().save(customer);
//	}
//
//	public Page<Customer> findByLastname(String lastname, Pageable pageable)
//	{
//		return customerRepository().findByLastname(lastname, pageable);
//	}
}
