package com.usee.sky.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.usee.sky.model.Message;

@Component("messageWriter")
public class MessagesItemWriter implements ItemWriter<Message>
{

	public void write(List<? extends Message> messages) throws Exception
	{
		System.out.println("write results");
		for (Message m : messages)
		{
			System.out.println(m.getContent());
		}
	}
}
