/***********************************************************************
 *
 * 
 *
 * @file        PersonJob.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月9日
 *
 *
 ***********************************************************************/
package com.usee.sky.batch.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.usee.sky.batch.processor.PersonItemProcessor;
import com.usee.sky.model.Person;

/**
 * @author jet
 *
 */
@Component
public class PersonJob extends AbstractBatchJob
{
	@Bean
	public ItemReader<Person> reader()
	{
		System.out.println("*****Item Reader*****");
		FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
		reader.setResource(new ClassPathResource("sample-data.csv"));
		reader.setLineMapper(new DefaultLineMapper<Person>()
		{
			{
				setLineTokenizer(new DelimitedLineTokenizer()
				{
					{
						setNames(new String[]
						{
								"firstName", "lastName"
						});
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>()
				{
					{
						setTargetType(Person.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public ItemProcessor<Person, Person> processor()
	{
		System.out.println("*****Item Processor*****");
		return new PersonItemProcessor();
	}

	@Bean
	public ItemWriter<Person> writer(DataSource dataSource)
	{
		System.out.println("*****Item Writer*****");
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public Job importUserJob(Step s1)
	{
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer())
				.flow(s1).end().build();
	}

	@Bean
	public Step step1(ItemReader<Person> reader, ItemWriter<Person> writer,
			ItemProcessor<Person, Person> processor)
	{
		return stepBuilderFactory.get("step1").<Person, Person> chunk(10)
				.reader(reader).processor(processor).writer(writer).build();
	}

}
