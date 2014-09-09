package com.usee.sky.batch.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan("com.usee.sky.batch")
@PropertySource("classpath:batch.properties")
@EnableBatchProcessing
public class BatchConfiguration
{


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
	public JobBuilderFactory jobBuilderFactory()
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
	public JdbcTemplate jdbcTemplate()
	{
		return new JdbcTemplate(batchDataSource());
	}

}
