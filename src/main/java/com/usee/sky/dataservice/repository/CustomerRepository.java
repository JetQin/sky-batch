/***********************************************************************
 *
 * 
 *
 * @file        CustomerRepository.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.dataservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.usee.sky.model.Customer;

/**
 * @author jet
 *
 */
@Transactional(readOnly = true)
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
	 Page<Customer> findByLastname(String lastname, Pageable pageable); 
}
