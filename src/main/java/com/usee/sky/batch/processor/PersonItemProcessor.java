/***********************************************************************
 *
 * 
 *
 * @file        PersonItemProcessor.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月9日
 *
 *
 ***********************************************************************/
package com.usee.sky.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.usee.sky.model.Person;

/**
 * @author jet
 *
 */
public class PersonItemProcessor implements ItemProcessor<Person,Person>
{

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
	 */
	@Override
	public Person process(Person item) throws Exception
	{
        Person person = new Person();
        person.setFirstName(item.getFirstName().toUpperCase());
        person.setLastName(item.getLastName().toUpperCase());
		return person;
	}

}
