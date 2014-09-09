package com.usee.sky.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.usee.sky.model.Message;
import com.usee.sky.model.User;

@Component("messageProcessor")
public class MessagesItemProcessor implements ItemProcessor<User, Message>
{

	public Message process(User user) throws Exception
	{
		Message m = new Message();
		m.setContent("Hello " + user.getName()
				+ ",please pay promptly at the end of this month.");
		return m;
	}

}
