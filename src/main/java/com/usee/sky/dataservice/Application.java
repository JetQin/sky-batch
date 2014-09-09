package com.usee.sky.dataservice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.usee.sky.dataservice.conf.PersistenceJPAConfig;
import com.usee.sky.dataservice.conf.ServiceConfiguration;
import com.usee.sky.dataservice.repository.CustomerService;
//import com.usee.sky.dataservice.repository.CustomerService;
import com.usee.sky.dataservice.service.UserService;
import com.usee.sky.model.Customer;

@Configuration
@EnableScheduling
//@Import(ServiceConfiguration.class)
// @ImportResource("classpath:data.xml")
public class Application
{

//	@Scheduled(cron = "0/5 * * * * *", fixedDelay = 2000)
//	public static void findCustomer(UserService service, long id)
//	{
//		System.out.println("Customer found with findOne(1L):");
//		System.out.println("--------------------------------");
//		System.out.println(service.findOne(1L));
//		System.out.println();
//	}

	public static void main(String[] args)
	{

		ApplicationContext context = new AnnotationConfigApplicationContext(
				ServiceConfiguration.class);
		
		UserService repository = context.getBean(UserService.class);

//		repository.save(new Customer("Jack", "Bauer"));
//		repository.save(new Customer("Chloe", "O'Brian"));
//		repository.save(new Customer("Kim", "Bauer"));
//		repository.save(new Customer("David", "Palmer"));
//		repository.save(new Customer("Michelle", "Dessler"));
		
		// fetch an individual customer by ID
		 Customer customer = repository.findOne(4L);
		 System.out.println("Customer found with findOne(1L):");
		 System.out.println("--------------------------------");
		 System.out.println(customer);
		 System.out.println();
		 
//		 ApplicationContext jpaContext = new AnnotationConfigApplicationContext(
//				 PersistenceJPAConfig.class);
//		 
		 
//		 
//		CustomerService customerService = jpaContext.getBean(CustomerService.class);
//		PageRequest page = new PageRequest(5, 10);
//
//		// fetch all customers
//		Iterable<Customer> customers = customerService.findAll(page);
//		System.out.println("Customers found with findAll():");
//		System.out.println("-------------------------------");
//		for (Customer item : customers)
//		{
//			System.out.println(item);
//		}
//		System.out.println();
//
//	
//
//		// fetch customers by last name
//		Page<Customer> bauers = customerService.findByLastname("Bauer", page);
//		System.out.println("Customer found with findByLastName('Bauer'):");
//		System.out.println("--------------------------------------------");
//		for (Customer bauer : bauers)
//		{
//			System.out.println(bauer);
//		}

	}

}
