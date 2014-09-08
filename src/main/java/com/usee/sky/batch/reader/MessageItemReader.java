package com.usee.sky.batch.reader;

import javax.annotation.Resources;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.usee.sky.batch.mapper.UserMapper;

@Component("messageReader")
public class MessageItemReader extends FlatFileItemReader
{

	public MessageItemReader()
	{
		setLineMapper(lineMapper());
		setResource(csvDataResource());
		setStrict(false);
	}

	public LineMapper lineMapper()
	{
		DefaultLineMapper mapper = new DefaultLineMapper<String>();
		mapper.setLineTokenizer(new DelimitedLineTokenizer());
		mapper.setFieldSetMapper(new UserMapper());
		return mapper;
	}

	public Resource csvDataResource()
	{
		Resource resource = new ClassPathResource("classpath:users.txt");
		return resource;
	}

}
