package com.usee.sky.dataservice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.usee.sky.dataservice.conf.ServiceConfiguration;
import com.usee.sky.dataservice.service.UserService;
import com.usee.sky.model.Customer;

@Configuration
@EnableScheduling
@Import(ServiceConfiguration.class)
// @ImportResource("classpath:data.xml")
public class Application
{

	@Scheduled(cron = "0/5 * * * * *", fixedDelay = 2000)
	public static void findCustomer(UserService service, long id)
	{
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(service.findOne(1L));
		System.out.println();
	}

	public static void main(String[] args)
	{

		ApplicationContext context = new AnnotationConfigApplicationContext(
				Application.class);
		UserService repository = context.getBean(UserService.class);

		PageRequest page = new PageRequest(5, 10);

		// save a couple of customers
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		// Iterable<Customer> customers = repository.findAll(page);
		// System.out.println("Customers found with findAll():");
		// System.out.println("-------------------------------");
		// for (Customer customer : customers) {
		// System.out.println(customer);
		// }
		// System.out.println();

		// fetch an individual customer by ID
		// Customer customer = repository.findOne(1L);
		// System.out.println("Customer found with findOne(1L):");
		// System.out.println("--------------------------------");
		// System.out.println(customer);
		// System.out.println();

		// fetch customers by last name
		// Page<Customer> bauers = repository.findByLastname("Bauer",page);
		// System.out.println("Customer found with findByLastName('Bauer'):");
		// System.out.println("--------------------------------------------");
		// for (Customer bauer : bauers) {
		// System.out.println(bauer);
		// }

		// context.close();
	}

}
