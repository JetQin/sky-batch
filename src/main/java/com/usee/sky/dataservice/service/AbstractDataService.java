/***********************************************************************
 *
 * 
 *
 * @file        AbstractDataService.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.usee.sky.dataservice.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jet
 *
 */
@Component
@Transactional
public abstract class AbstractDataService<T extends Serializable>
{

	private Class<T> clazz;

	@Autowired
	@Qualifier("hibernateSessionFactory")
	private SessionFactory sessionFactory;

	public void setClazz(final Class<T> clazzToSet)
	{
		clazz = clazzToSet;
	}

	public T findOne(final long id)
	{
		return (T) getCurrentSession().get(clazz, id);
	}

	public List<T> findAll()
	{
		return getCurrentSession().createQuery("from " + clazz.getName())
				.list();
	}

	public void save(final T entity)
	{
		getCurrentSession().persist(entity);
	}

	public T update(final T entity)
	{
		return (T) getCurrentSession().merge(entity);
	}

	public void delete(final T entity)
	{
		getCurrentSession().delete(entity);
	}

	public void deleteById(final long id)
	{
		final T entity = findOne(id);
		delete(entity);
	}

	protected final Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
}
