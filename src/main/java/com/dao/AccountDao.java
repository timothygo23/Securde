package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Account;
import com.beans.BrandManufacturer;
import com.beans.Customer;

@Component
public class AccountDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void addBrandManufacturer(Account account, BrandManufacturer brandManufacturer) {
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			session.save(account);
			//sets the account id of brand manufacturer to the generated id after saving account
			brandManufacturer.setAccount_id(account.getAccount_id());
			session.save(brandManufacturer);
		}
	}
	
	@Transactional
	public void addCustomer(Account account, Customer customer) {
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			session.save(account);
			//sets the account id of customer to the generated id after saving account
			customer.setAccount_id(account.getAccount_id());
			session.save(customer);
		}
	}
	
}
