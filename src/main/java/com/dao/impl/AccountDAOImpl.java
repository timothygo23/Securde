package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Account;
import com.beans.BrandManufacturer;
import com.beans.Customer;
import com.dao.AccountDAO;

@Repository
public class AccountDAOImpl implements AccountDAO{
	
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
	public Account getByEmail(String email) {
		Account account = null;
		
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			List<Account> accs = session.createQuery("from Account where email =:email", Account.class).setParameter("email", email).getResultList();
			
			if(accs != null && !accs.isEmpty())
				account = accs.get(0);
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
	public int addCustomer(Account account, Customer customer) {
		int account_id = 0;
		int cartNum = 1; //always 1.
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			account_id = (int)session.save(account);
			//sets the account id of customer to the generated id after saving account
			customer.setAccount_id(account.getAccount_id());
			customer.setCart_num(cartNum);
			session.save(customer);
		}
		return account_id;
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
			bm = session.createQuery("from BrandManufacturer", BrandManufacturer.class).getResultList();
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
			customers = session.createQuery("from Customer", Customer.class).getResultList();
		}
		
		return customers;
	}

	@Transactional
	public void addAdmin(Account account) {
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			session.save(account);
		}
	}

	@Transactional
	public boolean isSaltUnique(byte[] salt) {
		Account account = null;
		
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			List<Account> accs = session.createQuery("from Account where salt =:salt", Account.class).setParameter("salt", salt).getResultList();
			
			if(accs != null && !accs.isEmpty())
				account = accs.get(0);
		}
		
		if(account == null)
			return true;
		else
			return false;
	}
	
	@Transactional
	public void update(Account account){
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			Account acc = session.find(Account.class, account.getAccount_id());
			acc.setAccount_type(account.getAccount_type());
			acc.setEmail(account.getEmail());
			acc.setPassword(account.getPassword());
			acc.setSalt(account.getSalt());
		}
	}
	
	@Transactional
	public void updateCustomerInfo(Customer customer){
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			session.update(customer);
		}
	}
}
