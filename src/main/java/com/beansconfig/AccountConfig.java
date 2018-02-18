package com.beansconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beans.Account;
import com.beans.BrandManufacturer;
import com.beans.Customer;

@Configuration
public class AccountConfig {

	@Bean
	public Account account() {
		return new Account();
	}
	
	@Bean
	public BrandManufacturer brandManufacturer() {
		return new BrandManufacturer();
	}
	
	@Bean
	public Customer customer() {
		return new Customer();
	}
}
