package com.services;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Account;
import com.beans.Customer;
import com.dao.AccountDao;

@Component
public class RegisterService {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private AccountDao accountDao;
	
	//Add account and customer.
	@Transactional
	public void addAccount(Account account, Customer customer){
		Session session = sessionFactory.getCurrentSession();
		
		customer.setAccount_id((int) session.save(account)); //"save" returns the ID of this entity.
		session.save(customer);
	}
	
	

}
