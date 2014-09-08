package com.usee.sky.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.usee.sky.model.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person>
{
	public Person process(Person person) throws Exception
	{
		String firstName = person.getFirstName().toUpperCase();
		String lastName = person.getLastName().toUpperCase();

		Person transformedPerson = new Person(firstName, lastName);

		System.out.println("Converting (" + person + ") into (" + transformedPerson + ")");

		return transformedPerson;
	}
}