/***********************************************************************
 *
 * 
 *
 * @file        AccountRepository.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.dataservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.usee.sky.model.Account;
import com.usee.sky.model.Customer;

/**
 * @author jet
 *
 */
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long>
{

	List<Account> findByCustomer(Customer customer);

}
