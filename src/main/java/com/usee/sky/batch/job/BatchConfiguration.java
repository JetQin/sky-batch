package com.usee.sky.batch.job;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.usee.sky.batch.processor.PersonItemProcessor;
import com.usee.sky.model.Person;

@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableBatchProcessing
public class BatchConfiguration
{

	// tag::readerwriterprocessor[]

	@Autowired
	Environment env;

	@Bean
	public JobRepository repository()
	{
		JobRepository repository = new SimpleJobRepository(
				new MapJobInstanceDao(), new MapJobExecutionDao(),
				new MapStepExecutionDao(), new MapExecutionContextDao());
		return repository;
	}

	@Bean
	public JobBuilderFactory jobs()
	{
		JobBuilderFactory factory = new JobBuilderFactory(repository());
		return factory;
	}

	@Bean
	public StepBuilderFactory stepBuilderFactory()
	{
		StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(
				repository(), transactionManager());
		return stepBuilderFactory;
	}

	@Bean
	public SimpleJobLauncher launcher()
	{
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(repository());
		return launcher;
	}

	@Bean(name = "batchDataSource")
	public BasicDataSource batchDataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env
				.getProperty("batch.jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("batch.jdbc.url"));
		dataSource.setUsername(env.getProperty("batch.jdbc.user"));
		dataSource.setPassword(env.getProperty("batch.jdbc.password"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager()
	{
		return new DataSourceTransactionManager(batchDataSource());
	}

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

	// end::readerwriterprocessor[]

	// tag::jobstep[]
	@Bean
	public Job importUserJob(Step s1)
	{
		return jobs().get("importUserJob").incrementer(new RunIdIncrementer())
				.flow(s1).end().build();
	}

	@Bean
	public Step step1(ItemReader<Person> reader, ItemWriter<Person> writer,
			ItemProcessor<Person, Person> processor)
	{
		return stepBuilderFactory().get("step1").<Person, Person> chunk(10)
				.reader(reader).processor(processor).writer(writer).build();
	}

	// end::jobstep[]

	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		return new JdbcTemplate(batchDataSource());
	}

}
