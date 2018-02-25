package com.dao;

import java.util.List;

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
	public Account get(int account_id) {
		Account account = null;
		
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			account = session.find(Account.class, account_id);
		}
		
		return account;
	}
	
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

	@Transactional
	public BrandManufacturer getBrandManufacturer(int account_id){
		BrandManufacturer bm = null;
		
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			bm = session.find(BrandManufacturer.class, account_id);
		}
		
		return bm;
	}
	
	@Transactional
	public List<BrandManufacturer> getAllBrandManufacturers(){
		List<BrandManufacturer> bm = null;
		
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			bm = session.createQuery("from brand_manufacturer", BrandManufacturer.class).getResultList();
		}
		
		return bm;
	}
	
	@Transactional
	public Customer getCustomer(int account_id){
		Customer customer = null;
		
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			customer = session.find(Customer.class, account_id);
		}
		
		return customer;
	}
	
	@Transactional
	public List<Customer> getAllCustomers(){
		List<Customer> customers = null;
		
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			customers = session.createQuery("from customer", Customer.class).getResultList();
		}
		
		return customers;
	}
	
	
}
