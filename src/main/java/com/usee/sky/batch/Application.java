package com.usee.sky.batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.usee.sky.batch.configuration.BatchConfiguration;
import com.usee.sky.model.Person;

@ComponentScan
// @EnableAutoConfiguration
public class Application
{

	public static void main(String[] args) throws BeansException,
			JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException
	{
		// ApplicationContext ctx = SpringApplication.run(Application.class,
		// args);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				BatchConfiguration.class);
		SimpleJobLauncher launcher = ctx.getBean(SimpleJobLauncher.class);
		launcher.run(ctx.getBean(Job.class), new JobParameters());

		List<Person> results = ctx.getBean(JdbcTemplate.class).query(
				"SELECT first_name, last_name FROM people",
				new RowMapper<Person>()
				{
					@Override
					public Person mapRow(ResultSet rs, int row)
							throws SQLException
					{
						return new Person(rs.getString(1), rs.getString(2));
					}
				});

		for (Person person : results)
		{
			System.out.println("Found <" + person + "> in the database.");
		}
	}

}
