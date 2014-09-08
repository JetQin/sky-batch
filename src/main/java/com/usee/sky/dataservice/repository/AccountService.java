/***********************************************************************
 *
 * 
 *
 * @file        AccountService.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.dataservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.usee.sky.model.Account;
import com.usee.sky.model.Customer;

/**
 * @author jet
 *
 */
@Repository
@Transactional(readOnly = true)
class AccountService
{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private AccountRepository repository;

	@Transactional
	public Account save(Account account)
	{

//		if (account.getId() == null)
//		{
//			em.persist(account);
//			return account;
//		}
//		else
//		{
//			return em.merge(account);
//		}
		return repository.save(account);
	}

	public List<Account> findByCustomer(Customer customer)
	{

//		TypedQuery query = em.createQuery("select a from Account a where a.customer = ?1", Account.class);
//		query.setParameter(1, customer);
//
//		return query.getResultList();
		return repository.findByCustomer(customer);
	}
}
